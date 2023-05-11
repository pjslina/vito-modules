package com.vito.springboot.exception;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.vito.framework.dto.Response;
import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.regex.Pattern;

/**
 * @author panjin
 */
@ResponseBody
@Slf4j
public class DefaultGrpcExceptionAdvice {

    private static final Pattern pattern = Pattern.compile("\\{(\\d+)\\}");

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(StatusRuntimeException.class)
    public Response handleException(StatusRuntimeException e) {
        Metadata metadata = e.getTrailers();
        if (metadata != null) {
            String errorCode = metadata.get(Metadata.Key.of("errorCode", Metadata.ASCII_STRING_MARSHALLER));
            String errorMessage = metadata.get(Metadata.Key.of("errorMessage", Metadata.ASCII_STRING_MARSHALLER));
            String args = metadata.get(Metadata.Key.of("args", Metadata.ASCII_STRING_MARSHALLER));
            if (errorCode != null && errorMessage != null) {
                errorCode = Base64.decodeStr(errorCode);
                errorMessage = Base64.decodeStr(errorMessage);
                Object[] objects = JSONUtil.toList(JSONUtil.parseArray(Base64.decodeStr(args)), Object.class).toArray();
                String message = null;
                try {
                    message = messageSource.getMessage(errorCode, objects, LocaleContextHolder.getLocale());
                } catch (NoSuchMessageException ex) {
                    log.warn("Please add the resource of {} to the internationalized resource file", errorCode);
                    message = ExceptionUtil.parseMessage(errorMessage, objects);
                }
                return Response.buildFailure(errorCode, message);
            }
        }
        return Response.buildFailure("GrpcError", e.getMessage());
    }

}
