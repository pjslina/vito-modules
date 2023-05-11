package com.vito.bank.domain.types;

import com.vito.bank.common.enums.BankErrorCodeEnum;
import com.vito.framework.exception.Assert;
import com.vito.framework.exception.BizException;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author panjin
 */
@Data
public class Money {

    private BigDecimal amount;

    private Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        Assert.notNull(amount, BankErrorCodeEnum.AMOUNT_IS_NULL);
        this.amount = amount;
        this.currency = currency;
    }

    public int compareTo(Money money) {
        return this.amount.compareTo(money.getAmount());
    }

    public Money subtract(Money money) {
        Assert.isTrue(this.currency.getValue().equals(money.getCurrency().getValue()), BankErrorCodeEnum.CURRENCY_IS_ERROR);
        Assert.isTrue(this.amount.compareTo(money.getAmount()) >= 0, BankErrorCodeEnum.BALANCE_IS_NOT_ENOUGH);
        BigDecimal resultAmount = this.amount.subtract(money.getAmount());
        return new Money(resultAmount, this.currency);
    }

    public Money add(Money money) throws BizException {
        Assert.isTrue(this.currency.getValue().equals(money.getCurrency().getValue()), BankErrorCodeEnum.CURRENCY_IS_ERROR);
        BigDecimal resultAmount = this.amount.add(money.getAmount());
        return new Money(resultAmount, this.currency);
    }
}
