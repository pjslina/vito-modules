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
    FILE_NAME_ILLEGAL("1006", "文件名不合法"),
    DIR_ALREADY_EXISTS("1007", "目录已存在"),
    DIR_NOT_EXISTS("1008", "目录不存在"),
    DIR_NOT_EMPTY("1009", "目录不为空"),
    S3_PATH_IS_BLANK("1010", "S3路径为空"),
    S3_PATH_START_WITH_SPLIT("1011", "S3路径不以/开头"),
    S3_PATH_NOT_END_WITH_SPLIT("1012", "S3路径不以/结尾"),
    S3_FILE_ALREADY_EXISTS("1013", "S3文件已存在"),
    S3_PATH_CONTAINS_DOUBLE_SLASH("1014", "S3路径包含双斜杠"),
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
