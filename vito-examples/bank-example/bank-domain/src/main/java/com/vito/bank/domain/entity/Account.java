package com.vito.bank.domain.entity;

import com.vito.bank.common.enums.BankErrorCodeEnum;
import com.vito.bank.domain.types.*;
import com.vito.framework.exception.Assert;
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
        Assert.isTrue(this.available.compareTo(money) < 0, BankErrorCodeEnum.BALANCE_IS_NOT_ENOUGH);
        Assert.isTrue(this.dailyLimit.compareTo(money) < 0, BankErrorCodeEnum.QUOTA_IS_NOT_ENOUGH);

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
        Assert.isFalse(this.getCurrency().equals(money.getCurrency()), BankErrorCodeEnum.CURRENCY_IS_ERROR);
        this.available = this.available.add(money);
    }
}
