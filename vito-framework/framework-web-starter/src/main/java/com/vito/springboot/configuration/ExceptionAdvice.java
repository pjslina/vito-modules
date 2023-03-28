package com.vito.springboot.configuration;

import com.vito.springboot.exception.DefaultExceptionAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author panjin
 */
@ControllerAdvice
public class ExceptionAdvice extends DefaultExceptionAdvice {
}
