package com.vito.framework.grpc.client.config;

import com.vito.framework.grpc.client.interceptor.LogGrpcInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

/**
 * @author panjin
 */
public class GrpcClientAutoConfiguration {

    @GrpcGlobalClientInterceptor
    @ConditionalOnMissingBean(value = {LogGrpcInterceptor.class})
    LogGrpcInterceptor logGrpcInterceptor() {
        return new LogGrpcInterceptor();
    }
}
