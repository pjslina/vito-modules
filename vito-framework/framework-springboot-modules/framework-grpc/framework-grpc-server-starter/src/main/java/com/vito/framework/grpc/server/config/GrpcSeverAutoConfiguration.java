package com.vito.framework.grpc.server.config;

import com.vito.framework.grpc.server.interceptor.LogGrpcInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

/**
 * @author panjin
 */
public class GrpcSeverAutoConfiguration {

    @GrpcGlobalServerInterceptor
    @ConditionalOnMissingBean(value = {LogGrpcInterceptor.class})
    LogGrpcInterceptor logGrpcInterceptor() {
        return new LogGrpcInterceptor();
    }

}
