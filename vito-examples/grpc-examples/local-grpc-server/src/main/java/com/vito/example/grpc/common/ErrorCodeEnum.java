package com.vito.example.grpc.common;

import com.vito.framework.exception.BaseCode;

/**
 * @author panjin
 */
public enum ErrorCodeEnum implements BaseCode {

    /** 记录不存在 */
    RES_IS_NULL("10000", "记录不存在"),
    ;

    private String errorCode;
    private String errorMessage;
    private Object[] args;

    ErrorCodeEnum(String errorCode, String errorMessage) {
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

    public ErrorCodeEnum setArgs(Object[] args) {
        this.args = args;
        return this;
    }
}
