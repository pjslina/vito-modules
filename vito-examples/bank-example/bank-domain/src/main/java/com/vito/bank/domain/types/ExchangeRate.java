package com.vito.bank.domain.types;

import com.vito.bank.common.enums.BankErrorCodeEnum;
import com.vito.framework.exception.Assert;

import java.math.BigDecimal;

/**
 * @author panjin
 */
public class ExchangeRate {

    private BigDecimal rage;
    private Currency source;
    private Currency target;

    public ExchangeRate(BigDecimal rage, Currency source, Currency target) {
        this.rage = rage;
        this.source = source;
        this.target = target;
    }

    public Money exchangeTo(Money sourceMoney) {
        Assert.isTrue(this.target.getValue().equals(sourceMoney.getCurrency().getValue()), BankErrorCodeEnum.CURRENCY_IS_ERROR);
        BigDecimal targetMount = sourceMoney.getAmount().multiply(rage);
        return new Money(targetMount, source);
    }
}
