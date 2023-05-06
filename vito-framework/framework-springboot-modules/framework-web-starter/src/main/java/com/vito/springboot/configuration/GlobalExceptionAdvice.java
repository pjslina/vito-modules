package com.vito.springboot.configuration;

import com.vito.springboot.exception.DefaultGlobalExceptionAdvice;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 全局异常处理
 * 如果有多个类使用ControllerAdvice注解，则Exception单独的放在一个类中
 * @author panjin
 */
@ControllerAdvice
@Order(100)
public class GlobalExceptionAdvice extends DefaultGlobalExceptionAdvice {
}
