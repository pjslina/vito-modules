package com.vito.bank.domain.types;

import com.vito.framework.exception.BizException;
import com.vito.framework.exception.ExceptionFactory;
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

        if (amount == null) {
            throw ExceptionFactory.bizException("金额不能为空");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public int compareTo(Money money) {
        return this.amount.compareTo(money.getAmount());
    }

    public Money subtract(Money money) {
        BigDecimal resultAmout = this.amount.subtract(money.getAmount());
        return new Money(resultAmout, this.currency);
    }

    public Money add(Money money) throws BizException {
        BigDecimal resultAmout = this.amount.add(money.getAmount());
        return new Money(resultAmout, this.currency);
    }
}
