package com.vito.bank.domain.entity;

import com.vito.bank.domain.types.Currency;
import com.vito.bank.domain.types.Money;
import com.vito.framework.exception.BizException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MoneyTest {

    @Test
    public void testSubtract() {
        Money money = new Money(new BigDecimal(100), new Currency("CNY"));
        Money money1 = new Money(new BigDecimal(100), new Currency("CNY"));
        Money money2 = money.subtract(money1);
        Assertions.assertEquals(money2.getAmount(), new BigDecimal(0));
    }

    @Test
    public void testSubtractWithDiffCurrency() {
        Money money = new Money(new BigDecimal(100), new Currency("USD"));
        Money money1 = new Money(new BigDecimal(100), new Currency("CNY"));
        Assertions.assertThrowsExactly(BizException.class, () -> money.subtract(money1));
    }

    @Test
    public void testSubtractWithNegative() {
        Money money = new Money(new BigDecimal(100), new Currency("USD"));
        Money money1 = new Money(new BigDecimal(300), new Currency("USD"));
        Assertions.assertThrowsExactly(BizException.class, () -> money.subtract(money1));
    }

    @Test
    public void testAdd() {
        Money money = new Money(new BigDecimal(100), new Currency("USD"));
        Money money1 = new Money(new BigDecimal(300), new Currency("USD"));
        Money money2 = money.add(money1);
        Assertions.assertEquals(money2.getAmount(), new BigDecimal(400));
    }
}
