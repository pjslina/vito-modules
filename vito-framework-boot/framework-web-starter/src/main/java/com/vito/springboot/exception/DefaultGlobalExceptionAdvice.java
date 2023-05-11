package com.vito.springboot.exception;

import com.vito.framework.dto.Response;
import com.vito.framework.exception.SysErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * grpc异常处理
 * @author panjin
 */
@ResponseBody
@Slf4j
public class DefaultGlobalExceptionAdvice {

    /**
     * 所有异常统一处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        return ExceptionUtil.defHandler(SysErrorCodeEnum.UNKNOWN_ERROR, e);
    }
}
