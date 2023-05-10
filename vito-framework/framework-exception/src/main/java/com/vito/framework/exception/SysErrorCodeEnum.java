package com.vito.framework.exception;

/**
 * @author panjin
 */
public enum SysErrorCodeEnum implements BaseCode {

    /** 非业务异常 */
    UNKNOWN_ERROR("1000", "未知异常"),
    ARGUMENT_ERROR("1001", "参数解析失败"),
    ACCESS_DENIED("1002", "没有权限"),
    METHOD_NOT_SUPPORTED("1003", "不支持的请求"),
    MEDIA_TYPE_NOT_SUPPORTED("1004", "不支持的媒体类型"),
    TIME_FORMAT_ERROR("1005", "时间格式不正确"),
    ;

    SysErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private String errorCode;
    private String errorMessage;
    private Object[] args;

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

    public SysErrorCodeEnum setArgs(Object[] args) {
        this.args = args;
        return this;
    }
}
