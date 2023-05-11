package com.vito.bank.domain.service.impl;

import com.vito.bank.domain.entity.Account;
import com.vito.bank.gateway.transfer.AccountTransferGateway;
import com.vito.bank.domain.types.ExchangeRate;
import com.vito.bank.domain.types.Money;
import org.springframework.stereotype.Service;

/**
 * @author panjin
 */
@Service
public class AccountTransferServiceImpl implements AccountTransferGateway {

    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) {
        Money sourceMoney = exchangeRate.exchangeTo(targetMoney);
        sourceAccount.withdraw(sourceMoney);
        targetAccount.deposit(targetMoney);
    }
}
