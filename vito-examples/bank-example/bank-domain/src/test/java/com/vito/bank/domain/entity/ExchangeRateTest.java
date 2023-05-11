package com.vito.bank.domain.entity;

import com.vito.bank.domain.types.Currency;
import com.vito.bank.domain.types.ExchangeRate;
import com.vito.bank.domain.types.Money;
import com.vito.framework.exception.BizException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ExchangeRateTest {

    @Test
    public void testExchangeRate() {
        ExchangeRate exchangeRate = new ExchangeRate(new BigDecimal("0.15"), new Currency("CNY"), new Currency("USD"));
        Money exchange = exchangeRate.exchangeTo(new Money(new BigDecimal("100"), new Currency("USD")));
        Assertions.assertEquals(new BigDecimal("15.00"), exchange.getAmount());
    }

    @Test
    public void testExchangeRateForException() {
        ExchangeRate exchangeRate = new ExchangeRate(new BigDecimal("0.15"), new Currency("GBP"), new Currency("USD"));
        Assertions.assertThrowsExactly(BizException.class, () -> {
            exchangeRate.exchangeTo(new Money(new BigDecimal("100"), new Currency("CNY")));
        });
    }
}
