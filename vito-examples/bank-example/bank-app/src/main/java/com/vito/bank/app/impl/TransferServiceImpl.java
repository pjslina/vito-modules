package com.vito.bank.app.impl;

import com.vito.bank.api.TransferService;
import com.vito.bank.domain.entity.Account;
import com.vito.bank.domain.types.*;
import com.vito.bank.dto.TransferCmd;
import com.vito.bank.gateway.AccountGateway;
import com.vito.bank.gateway.exchange.ExchangeRateGateway;
import com.vito.bank.gateway.message.AuditMessageProducer;
import com.vito.bank.gateway.transfer.AccountTransferGateway;
import com.vito.framework.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author panjin
 */
@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private ExchangeRateGateway exchangeRateGateway;
    @Autowired
    private AuditMessageProducer auditMessageProducer;
    @Autowired
    private AccountTransferGateway accountTransferGateway;
    @Autowired
    private AccountGateway accountGateway;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Response transfer(TransferCmd transferCmd) {
        Money targetMoney = new Money(transferCmd.getTargetAmount(), new Currency(transferCmd.getTargetCurrency()));
        Account sourceAccount = accountGateway.find(new UserId(transferCmd.getSourceUserId()));
        Account targetAccount = accountGateway.find(new AccountNumber(transferCmd.getTargetAccountNumber()));
        ExchangeRate exchangeRate = exchangeRateGateway.getExchangeRate(BigDecimal.ONE, sourceAccount.getCurrency(), targetAccount.getCurrency());
        accountTransferGateway.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);
        accountGateway.save(sourceAccount);
        accountGateway.save(targetAccount);
        // 发送审计消息
        AuditMessage message = new AuditMessage(sourceAccount, targetAccount, targetMoney);
        auditMessageProducer.send(message);
        return Response.buildSuccess();
    }
}
