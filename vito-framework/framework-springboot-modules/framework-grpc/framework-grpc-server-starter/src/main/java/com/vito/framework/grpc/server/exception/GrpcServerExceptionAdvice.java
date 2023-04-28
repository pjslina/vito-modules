package com.vito.framework.grpc.server.exception;

import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

/**
 * @author panjin
 */
@Slf4j
public class GrpcServerExceptionAdvice {

    @GrpcExceptionHandler
    public Status handleInvalidArgument(IllegalArgumentException e) {
        log.error("参数不合法", e);
        return Status.INVALID_ARGUMENT.withDescription(e.getMessage()).withCause(e);
    }
}
