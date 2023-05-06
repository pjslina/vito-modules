package com.vito.springboot.exception;

import com.vito.framework.dto.Response;
import com.vito.framework.exception.BaseCode;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
class ExceptionUtil {

    private static final Pattern pattern = Pattern.compile("\\{(\\d+)\\}");

    private ExceptionUtil() {
    }

    static Response defHandler(BaseCode error, Exception e) {
        log.error(error.getErrorMessage(), e);
        return Response.buildFailure(error.getErrorCode(), error.getErrorMessage());
    }

    /**
     * 如果没有配置国际化，则拿ErrorMessage和Args组装数据返回
     * @param baseCode
     * @return
     */
    static String parseMessage(BaseCode baseCode) {
        Object[] args = baseCode.getArgs();
        if (null == args || args.length == 0) {
            return baseCode.getErrorMessage();
        }
        return getString(baseCode.getErrorMessage(), args);
    }

    static String parseMessage(String errorMessage, Object[] args) {
        if (null == args || args.length == 0) {
            return errorMessage;
        }
        return getString(errorMessage, args);
    }

    private static String getString(String errorMessage, Object[] args) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = pattern.matcher(errorMessage);
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
