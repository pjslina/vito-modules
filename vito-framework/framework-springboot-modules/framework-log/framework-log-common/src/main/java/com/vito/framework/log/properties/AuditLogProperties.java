package com.vito.framework.log.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 审计日志配置
 * @author panjin
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "vito.audit-log")
public class AuditLogProperties {

    /**
     * 是否开启审计日志
     */
    private Boolean enabled = false;
    /**
     * 日志记录类型(logger/db/redis/es)
     */
    private String logType;
}
