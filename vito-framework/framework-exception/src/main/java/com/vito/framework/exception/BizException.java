package com.vito.framework.exception;

/**
 * @author panjin
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BizException(BaseCode baseCode) {
        super(baseCode);
    }

    public BizException(BaseCode baseCode, Throwable e) {
        super(baseCode, e);
    }
}
