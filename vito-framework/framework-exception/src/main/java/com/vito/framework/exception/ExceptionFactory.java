package com.vito.framework.exception;

/**
 * Exception Factory
 * @author panjin
 */
public class ExceptionFactory {

    public static BizException bizException(BaseCode baseCode) {
        return new BizException(baseCode);
    }

    public static BizException bizException(BaseCode baseCode, Throwable e) {
        return new BizException(baseCode, e);
    }

    public static SysException sysException(BaseCode baseCode) {
        return new SysException(baseCode);
    }

    public static SysException sysException(BaseCode baseCode, Throwable e) {
        return new SysException(baseCode, e);
    }
}
