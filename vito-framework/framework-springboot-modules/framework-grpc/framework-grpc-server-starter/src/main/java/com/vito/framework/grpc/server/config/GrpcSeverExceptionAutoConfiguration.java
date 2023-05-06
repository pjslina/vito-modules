package com.vito.framework.grpc.server.config;

import org.springframework.context.annotation.ComponentScan;

/**
 * 处理grpc服务端异常的扫描配置类
 * @author panjin
 */
@ComponentScan(basePackages = {"com.vito.framework.grpc.server.exception"})
public class GrpcSeverExceptionAutoConfiguration {

}
