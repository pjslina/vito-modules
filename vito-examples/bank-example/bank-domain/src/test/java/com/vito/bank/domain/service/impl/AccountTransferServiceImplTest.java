package com.vito.bank.domain.service.impl;

import com.vito.bank.domain.entity.Account;
import com.vito.bank.domain.types.Currency;
import com.vito.bank.domain.types.ExchangeRate;
import com.vito.bank.domain.types.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountTransferServiceImplTest {

    @Test
    public void testTransfer() {
        AccountTransferServiceImpl accountTransferServiceImpl = new AccountTransferServiceImpl();
        Account sourceAccount = new Account();
        sourceAccount.setCurrency(new Currency("CNY"));
        sourceAccount.setAvailable(new Money(new BigDecimal("100"), new Currency("CNY")));
        sourceAccount.setDailyLimit(new Money(new BigDecimal("1000"), new Currency("CNY")));

        Account targetAccount = new Account();
        targetAccount.setCurrency(new Currency("CNY"));
        targetAccount.setAvailable(new Money(new BigDecimal("0"), new Currency("CNY")));

        Money targetMoney = new Money(new BigDecimal("100"), new Currency("CNY"));
        ExchangeRate exchangeRate = new ExchangeRate(new BigDecimal("1.0"), new Currency("CNY"), new Currency("CNY"));
        accountTransferServiceImpl.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);
        Assertions.assertEquals(new BigDecimal("0.0"), sourceAccount.getAvailable().getAmount());
        Assertions.assertEquals(new BigDecimal("100"), targetAccount.getAvailable().getAmount());
        Assertions.assertEquals(new BigDecimal("900.0"), sourceAccount.getDailyLimit().getAmount());
    }

    @Test
    public void testTransferWithDiffCurrency() {
        AccountTransferServiceImpl accountTransferServiceImpl = new AccountTransferServiceImpl();
        Account sourceAccount = new Account();
        sourceAccount.setCurrency(new Currency("CNY"));
        sourceAccount.setAvailable(new Money(new BigDecimal("100"), new Currency("CNY")));
        sourceAccount.setDailyLimit(new Money(new BigDecimal("1000"), new Currency("CNY")));

        Account targetAccount = new Account();
        targetAccount.setCurrency(new Currency("USD"));
        targetAccount.setAvailable(new Money(new BigDecimal("0"), new Currency("USD")));

        Money targetMoney = new Money(new BigDecimal("15"), new Currency("USD"));
        ExchangeRate exchangeRate = new ExchangeRate(new BigDecimal("6.5"), new Currency("CNY"), new Currency("USD"));
        accountTransferServiceImpl.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);
        Assertions.assertEquals(new BigDecimal("2.5"), sourceAccount.getAvailable().getAmount());
        Assertions.assertEquals(new BigDecimal("15"), targetAccount.getAvailable().getAmount());
        Assertions.assertEquals(new BigDecimal("902.5"), sourceAccount.getDailyLimit().getAmount());
    }
}
