package com.vito.springboot.exception;

import com.vito.framework.dto.Response;
import com.vito.framework.exception.BaseException;
import com.vito.framework.exception.BizException;
import com.vito.framework.exception.SysErrorCodeEnum;
import com.vito.framework.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

/**
 * @author panjin
 */
@ResponseBody
@Slf4j
public class DefaultExceptionAdvice {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = null;
        try {
            // 第一个参数对应资源文件的key
            message = messageSource.getMessage(fieldError.getDefaultMessage(), null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException ex) {
            log.warn("Please add the resource of {} to the internationalized resource file", e.getMessage());
            message = fieldError.getDefaultMessage();
        }
        return Response.buildFailure("MethodArgumentError", message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BizException.class)
    public Response handleBizException(BizException e) {
        return handleBaseException(e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SysException.class)
    public Response handleSysException(SysException e) {
        return handleBaseException(e);
    }

    private Response handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        String message = null;
        try {
            message = messageSource.getMessage(e.getBaseCode().getErrorCode(), e.getBaseCode().getArgs(), LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException ex) {
            log.warn("Please add the resource of {} to the internationalized resource file", e.getBaseCode().getErrorCode());
            message = ExceptionUtil.parseMessage(e.getBaseCode());
        }
        return Response.buildFailure(e.getBaseCode().getErrorCode(), message);
    }

    /**
     * 参数不合法异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public Response badRequestException(IllegalArgumentException e) {
        return ExceptionUtil.defHandler(SysErrorCodeEnum.ARGUMENT_ERROR, e);
    }

    /**
     * AccessDeniedException异常处理返回json
     * 返回状态码:403
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public Response badMethodExpressException(AccessDeniedException e) {
        return ExceptionUtil.defHandler(SysErrorCodeEnum.ACCESS_DENIED, e);
    }

    /**
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ExceptionUtil.defHandler(SysErrorCodeEnum.METHOD_NOT_SUPPORTED, e);
    }

    /**
     * 返回状态码:415
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Response handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return ExceptionUtil.defHandler(SysErrorCodeEnum.MEDIA_TYPE_NOT_SUPPORTED, e);
    }

}
