package com.vito.framework.log.configuration;

import com.vito.framework.log.aspect.AuditLogAspect;
import com.vito.framework.log.properties.AuditLogProperties;
import com.vito.framework.log.properties.LazyFacotryBean;
import com.vito.framework.log.properties.LogDbProperties;
import com.vito.framework.log.service.IAuditService;
import com.vito.framework.log.service.impl.DbAuditServiceImpl;
import com.vito.framework.log.service.impl.LoggerAuditServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * 日志自动配置
 * @author panjin
 */
@EnableConfigurationProperties({AuditLogProperties.class})
public class LogAutoConfiguration {

    /**
     * 日志数据库配置
     */
    @Configuration
    @ConditionalOnClass(HikariConfig.class)
    @EnableConfigurationProperties(LogDbProperties.class)
    public static class LogDbAutoConfigure {}

    @Bean
    @ConditionalOnProperty(name = "vito.audit-log.log-type", havingValue = "logger", matchIfMissing = true)
    public LoggerAuditServiceImpl loggerAuditService() {
        return new LoggerAuditServiceImpl();
    }

    /**
     * LogDbProperties使用懒加载方式创建，因为这个类继承了HikariConfig，
     * 不使用懒加载，且没有引入Hikari依赖的情况下会报错
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    @ConditionalOnProperty(name = "vito.audit-log.log-type", havingValue = "db")
    @ConditionalOnClass({JdbcTemplate.class})
    public DbAuditServiceImpl auditService(DataSource dataSource) throws Exception {
        LazyFacotryBean facotryBean = new LazyFacotryBean("com.vito.framework.log.properties.LogDbProperties");
        return new DbAuditServiceImpl((LogDbProperties)facotryBean.getObject(), dataSource);
    }

    @Bean
    @ConditionalOnClass({HttpServletRequest.class, RequestContextHolder.class})
    public AuditLogAspect auditLogAspect(AuditLogProperties auditLogProperties, IAuditService auditService) {
        return new AuditLogAspect(auditLogProperties, auditService);
    }
}
