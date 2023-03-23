package com.vito.bank.infra.persistence.impl;

import com.vito.bank.domain.entity.Account;
import com.vito.bank.domain.types.*;
import com.vito.bank.infra.persistence.AccountBuilder;
import com.vito.bank.infra.persistence.AccountDO;
import org.springframework.stereotype.Component;

/**
 * @author panjin
 */
@Component
public class AccountBuilderImpl implements AccountBuilder {

    @Override
    public Account toAccount(AccountDO accountDO) {
        Account account = new Account();
        account.setId(new AccountId(accountDO.getId()));
        account.setAccountNumber(new AccountNumber(accountDO.getAccountNumber()));
        account.setUserId(new UserId(accountDO.getUserId()));
        Currency currency = new Currency(accountDO.getCurrency());
        account.setCurrency(currency);
        account.setAvailable(new Money(accountDO.getAvailableAmount(), currency));
        account.setDailyLimit(new Money(accountDO.getDailyLimitAmount(), currency));
        return account;
    }

    @Override
    public AccountDO fromAccount(Account account) {
        AccountDO accountDO = new AccountDO();
        if (account.getId() != null){
            accountDO.setId(account.getId().getValue());
        }
        accountDO.setUserId(account.getUserId().getId());
        accountDO.setAccountNumber(account.getAccountNumber().getValue());
        accountDO.setAvailableAmount(account.getAvailable().getAmount());
        accountDO.setDailyLimitAmount(account.getDailyLimit().getAmount());
        accountDO.setCurrency(account.getCurrency().getValue());
        return accountDO;
    }
}
