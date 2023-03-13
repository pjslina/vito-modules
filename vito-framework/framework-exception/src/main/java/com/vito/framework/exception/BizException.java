package com.vito.framework.exception;

/**
 * @author panjin
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BizException(int code, String message) {
        super(code, message);
    }

    public BizException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
