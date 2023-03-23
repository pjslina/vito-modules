package com.vito.bank.domain.entity;

import com.vito.bank.domain.types.*;
import com.vito.framework.exception.ExceptionFactory;
import lombok.Data;

/**
 * @author panjin
 */
@Data
public class Account {

    private AccountId id;
    private AccountNumber accountNumber;
    private UserId userId;
    private Money available;
    private Money dailyLimit;
    private Currency currency;

    /**
     * 转出
     *
     * @param money
     */
    public void withdraw(Money money) {
        if (this.available.compareTo(money) < 0) {
            throw ExceptionFactory.bizException("余额不足");
        }

        if (this.dailyLimit.compareTo(money) < 0) {
            throw ExceptionFactory.bizException("今日额度超限");
        }

        this.available = this.available.subtract(money);
        this.dailyLimit = this.dailyLimit.subtract(money);
    }

    /**
     * 转入
     *
     * @param money
     * @throws Exception
     */
    public void deposit(Money money) {
        if (!this.getCurrency().equals(money.getCurrency())) {
            throw ExceptionFactory.bizException("币种不正确");
        }

        this.available = this.available.add(money);
    }
}
