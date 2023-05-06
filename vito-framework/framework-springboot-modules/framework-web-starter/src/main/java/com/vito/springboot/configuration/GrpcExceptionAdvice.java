package com.vito.springboot.configuration;

import com.vito.springboot.exception.DefaultGrpcExceptionAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author panjin
 */
@ConditionalOnClass(name = "io.grpc.StatusRuntimeException")
@ControllerAdvice
@Order(98)
public class GrpcExceptionAdvice extends DefaultGrpcExceptionAdvice {
}
