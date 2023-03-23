package com.vito.bank.gateway.transfer;

import com.vito.bank.domain.entity.Account;
import com.vito.bank.domain.types.ExchangeRate;
import com.vito.bank.domain.types.Money;

/**
 * @author panjin
 */
public interface AccountTransferGateway {

    /**
     * 转账
     * @param sourceAccount
     * @param targetAccount
     * @param targetMoney
     * @param exchangeRate
     */
    void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate);
}
