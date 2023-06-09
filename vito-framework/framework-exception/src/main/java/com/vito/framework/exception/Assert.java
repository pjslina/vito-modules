package com.vito.framework.exception;

import java.util.Collection;
import java.util.Map;

/**
 * Assertion utility class that assists in validating arguments.
 *
 * <p>Useful for identifying programmer errors early and clearly at runtime.
 *
 * <p>For example, if the contract of a public method states it does not
 * allow {@code null} arguments, {@code Assert} can be used to validate that
 * contract.
 *
 * For example:
 *
 * <pre class="code">
 * Assert.notNull(clazz, "The class must not be null");
 * Assert.isTrue(i > 0, "The value must be greater than zero");</pre>
 *
 * @author Frank Zhang
 * @date 2019-01-13 11:49 AM
 */
public abstract class Assert {

    /**
     * 如果表达式为true，则不抛出异常<br>
     * Assert a boolean expression, throwing {@code BizException}
     *
     * for example
     *
     * <pre class="code">Assert.isTrue(i != 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
     *
     * @param expression a boolean expression
     * @param baseCode
     * @throws BizException if expression is {@code false}
     */
    public static void isTrue(boolean expression, BaseCode baseCode) {
        if (!expression) {
            throw new BizException(baseCode);
        }
    }

    /**
     * 如果表达式为false，则不抛出异常<br>
     * Assert a boolean expression, if expression is true, throwing {@code BizException}
     *
     * for example
     *
     * <pre class="code">Assert.isFalse(i == 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
     *
     * This is more intuitive than isTure.
     */
    public static void isFalse(boolean expression, BaseCode baseCode) {
        if (expression) {
            throw new BizException(baseCode);
        }
    }

    public static void notNull(Object object, BaseCode baseCode) {
        if (object == null) {
            throw new BizException(baseCode);
        }
    }

    public static void notEmpty(Collection<?> collection, BaseCode baseCode) {
        if (collection == null || collection.isEmpty()) {
            throw new BizException(baseCode);
        }
    }

    public static void notEmpty(Map<?, ?> map, BaseCode baseCode) {
        if (map == null || map.isEmpty()) {
            throw new BizException(baseCode);
        }
    }
}
