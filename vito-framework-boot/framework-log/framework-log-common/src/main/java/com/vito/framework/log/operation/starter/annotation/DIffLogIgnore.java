package com.vito.framework.log.operation.starter.annotation;

import java.lang.annotation.*;

/**
 * Ignore convert field
 *
 * @author wulang
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DIffLogIgnore {
}
