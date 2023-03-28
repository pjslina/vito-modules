package com.vito.framework.exception;

/**
 * @author panjin
 */
public class SysException extends BaseException {

    private static final long serialVersionUID = 1L;

    public SysException(BaseCode baseCode) {
        super(baseCode);
    }

    public SysException(BaseCode baseCode, Throwable e) {
        super(baseCode, e);
    }

}
