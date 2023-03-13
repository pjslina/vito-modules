package com.vito.framework.exception;

/**
 * Base Exception is the parent of all exceptions.
 * <p/>
 * @author panjin
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
