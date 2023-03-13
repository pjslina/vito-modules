package com.vito.framework.exception;

/**
 * @author panjin
 */
public class SysException extends BaseException {

    private static final long serialVersionUID = 1L;

    public SysException(int code, String message) {
        super(code, message);
    }

    public SysException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
