package com.vito.framework.exception;

/**
 * Base Exception is the parent of all exceptions.
 * <p/>
 * @author panjin
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BaseCode getBaseCode() {
        return baseCode;
    }

    private final BaseCode baseCode;

    public BaseException(BaseCode baseCode) {
        super(baseCode.getErrorMessage());
        this.baseCode = baseCode;
    }

    public BaseException(BaseCode baseCode, Throwable e) {
        super(baseCode.getErrorMessage(), e);
        this.baseCode = baseCode;
    }

}
