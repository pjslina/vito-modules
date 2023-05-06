package com.vito.springboot.configuration;

import com.vito.springboot.exception.DefaultExceptionAdvice;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author panjin
 */
@ControllerAdvice
@Order(99)
public class ExceptionAdvice extends DefaultExceptionAdvice {
}
