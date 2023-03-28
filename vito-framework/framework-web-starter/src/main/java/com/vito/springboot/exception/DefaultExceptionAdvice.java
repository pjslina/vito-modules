package com.vito.springboot.exception;

import com.vito.framework.dto.Response;
import com.vito.framework.exception.BaseCode;
import com.vito.framework.exception.BizException;
import com.vito.framework.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentException(MethodArgumentNotValidException e) {
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

    @ExceptionHandler(BizException.class)
    public Response handleBizException(BizException e) {
        String message = null;
        try {
            message = messageSource.getMessage(e.getBaseCode().getErrorCode(), e.getBaseCode().getArgs(), LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException ex) {
            log.warn("No internationalized resource found processing BizException. errorMessage: {}", e.getBaseCode().getErrorMessage());
            message = praseMessage(e.getBaseCode());
        }
        return Response.buildFailure(e.getBaseCode().getErrorCode(), message);
    }

    @ExceptionHandler(SysException.class)
    public Response handleSysException(SysException e) {
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
