package com.vito.framework.log.operation.starter.annotation;

import java.lang.annotation.*;

/**
 * all unannotated fields.
 *
 * @author wulang
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DiffLogAllFields {
}