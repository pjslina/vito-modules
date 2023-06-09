package com.vito.bank.common.enums;

import com.vito.framework.exception.BaseCode;

/**
 * @author panjin
 */
public enum BankErrorCodeEnum implements BaseCode {

    /** 金额 */
    AMOUNT_IS_NULL("10000", "金额不能为空"),
    AMOUNT_IS_NULL_WITH_PARAM("10001", "{0}金额不能为空"),
    BALANCE_IS_NOT_ENOUGH("10002", "余额不足"),
    QUOTA_IS_NOT_ENOUGH("10003", "今日额度已用完"),
    CURRENCY_IS_ERROR("10004", "币种不正确"),
    ACCOUNT_IS_NULL("10005", "账户{0}不存在"),
    ACCOUNT_IS_NULL_WITH_USER("10006", "用户ID为{0}的账户不存在"),
    ;

    private String errorCode;
    private String errorMessage;
    private Object[] args;

    BankErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public Object[] getArgs() {
        return this.args;
    }

    public BankErrorCodeEnum setArgs(Object[] args) {
        this.args = args;
        return this;
    }
}
