package com.vito.framework.grpc.server.exception;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.vito.framework.exception.BaseCode;
import com.vito.framework.exception.BizException;
import com.vito.framework.exception.SysException;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

import java.util.Optional;

/**
 * grpc服务端异常处理
 * 参考
 * https://yidongnan.github.io/grpc-spring-boot-starter/zh-CN/server/exception-handling.html
 * @author panjin
 */
@Slf4j
@GrpcAdvice
public class GrpcServerExceptionAdvice {

    @GrpcExceptionHandler(IllegalArgumentException.class)
    public Status handleInvalidArgument(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return Status.INVALID_ARGUMENT.withDescription(e.getMessage()).withCause(e);
    }

    @GrpcExceptionHandler(BizException.class)
    public StatusRuntimeException handleBizException(BizException e) {
        log.error(e.getMessage(), e);
        Status status = Status.UNKNOWN.withDescription(e.getMessage()).withCause(e);
        Metadata metadata = getMetadata(e.getBaseCode());
        return status.asRuntimeException(metadata);
    }

    @GrpcExceptionHandler(SysException.class)
    public StatusRuntimeException handleSysException(SysException e) {
        log.error(e.getMessage(), e);
        Status status = Status.INTERNAL.withDescription(e.getMessage()).withCause(e);
        Metadata metadata = getMetadata(e.getBaseCode());
        return status.asRuntimeException(metadata);
    }

    /**
     * 获取元数据
     * 说明：这里使用了Base64编码，是为了防止grpc传输时出现乱码
     * @param baseCode
     * @return
     */
    private Metadata getMetadata(BaseCode baseCode) {
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("errorCode", Metadata.ASCII_STRING_MARSHALLER), Base64.encode(baseCode.getErrorCode()));
        metadata.put(Metadata.Key.of("errorMessage", Metadata.ASCII_STRING_MARSHALLER), Base64.encode(baseCode.getErrorMessage()));
        if (Optional.ofNullable(baseCode.getArgs()).isPresent()) {
            metadata.put(Metadata.Key.of("args", Metadata.ASCII_STRING_MARSHALLER), Base64.encode(JSONUtil.toJsonStr(baseCode.getArgs())));
        }
        return metadata;
    }
}
