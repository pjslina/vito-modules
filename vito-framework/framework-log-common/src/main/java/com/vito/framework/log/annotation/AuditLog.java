package com.vito.framework.log.annotation;

import java.lang.annotation.*;

/**
 * @author panjin
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditLog {

    /**
     * 操作信息
     */
    String operation();
}
