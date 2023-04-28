package com.vito.springboot.exception;

import com.vito.framework.dto.Response;
import com.vito.framework.exception.BaseCode;
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
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author panjin
 */
@ResponseBody
@Slf4j
public class DefaultExceptionAdvice {

    @Autowired
    private MessageSource messageSource;

    private static final Pattern pattern = Pattern.compile("\\{(\\d+)\\}");

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
            log.warn("No internationalized resource found processing MethodArgumentNotValidException. errorMessage: {}", e.getMessage());
            message = fieldError.getDefaultMessage();
        }
        return Response.buildFailure("MethodArgumentError", message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BizException.class)
    public Response handleBizException(BizException e) {
        log.error(e.getMessage(), e);
        String message = null;
        try {
            message = messageSource.getMessage(e.getBaseCode().getErrorCode(), e.getBaseCode().getArgs(), LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException ex) {
            log.warn("No internationalized resource found processing BizException. errorMessage: {}", e.getBaseCode().getErrorMessage());
            message = praseMessage(e.getBaseCode());
        }
        return Response.buildFailure(e.getBaseCode().getErrorCode(), message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SysException.class)
    public Response handleSysException(SysException e) {
        log.error(e.getMessage(), e);
        String message = null;
        try {
            message = messageSource.getMessage(e.getBaseCode().getErrorCode(), e.getBaseCode().getArgs(), LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException ex) {
            log.warn("No internationalized resource found processing SysException. errorMessage: {}", e.getBaseCode().getErrorMessage());
            message = praseMessage(e.getBaseCode());
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
        return defHandler(SysErrorCodeEnum.ARGUMENT_ERROR, e);
    }

    /**
     * AccessDeniedException异常处理返回json
     * 返回状态码:403
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public Response badMethodExpressException(AccessDeniedException e) {
        return defHandler(SysErrorCodeEnum.ACCESS_DENIED, e);
    }

    /**
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return defHandler(SysErrorCodeEnum.METHOD_NOT_SUPPORTED, e);
    }

    /**
     * 返回状态码:415
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Response handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return defHandler(SysErrorCodeEnum.MEDIA_TYPE_NOT_SUPPORTED, e);
    }

    /**
     * 所有异常统一处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        return defHandler(SysErrorCodeEnum.UNKNOWN_ERROR, e);
    }

    private Response defHandler(BaseCode error, Exception e) {
        log.error(error.getErrorMessage(), e);
        return Response.buildFailure(error.getErrorCode(), error.getErrorMessage());
    }

    /**
     * 如果没有配置国际化，则拿ErrorMessage和Args组装数据返回
     * @param baseCode
     * @return
     */
    private String praseMessage(BaseCode baseCode) {
        Object[] args = baseCode.getArgs();
        if (null == args) {
            return baseCode.getErrorMessage();
        }
        Matcher matcher = pattern.matcher(baseCode.getErrorMessage());
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            MatchResult matchResult = matcher.toMatchResult();
            int index = Integer.parseInt(matchResult.group(1));
            String replacement = (args.length > index) ? args[index].toString() : "";
            matcher.appendReplacement(result, replacement);
        }
        matcher.appendTail(result);
        return result.toString();
    }

}
