package com.vito.bank.domain.entity;

import com.vito.bank.domain.types.Currency;
import com.vito.bank.domain.types.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    @Test
    public void testWithdraw() {
        Account account = new Account();
        account.setAvailable(new Money(new BigDecimal("100"), new Currency("CNY")));
        account.setDailyLimit(new Money(new BigDecimal("100"), new Currency("CNY")));
        account.withdraw(new Money(new BigDecimal("50"), new Currency("CNY")));
        assertEquals(new BigDecimal("50"), account.getAvailable().getAmount());
        assertEquals(new BigDecimal("50"), account.getDailyLimit().getAmount());
    }

    @Test
    public void testDeposit() {
        Account account = new Account();
        account.setAvailable(new Money(new BigDecimal("100"), new Currency("CNY")));
        account.setDailyLimit(new Money(new BigDecimal("100"), new Currency("CNY")));
        account.setCurrency(new Currency("CNY"));
        account.deposit(new Money(new BigDecimal("50"), new Currency("CNY")));
        assertEquals(new BigDecimal("150"), account.getAvailable().getAmount());
        assertEquals(new BigDecimal("100"), account.getDailyLimit().getAmount());
    }
}
