package com.vito.framework.exception;

/**
 * Exception Factory
 * @author panjin
 */
public class ExceptionFactory {

    public static BizException bizException(int code, String message) {
        return new BizException(code, message);
    }

    public static SysException sysException(int code, String message) {
        return new SysException(code, message);
    }

    public static SysException sysException(int code, String message, Throwable cause) {
        return new SysException(code, message, cause);
    }
}
