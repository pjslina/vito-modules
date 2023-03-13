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
     * Assert a boolean expression, throwing {@code BizException}
     *
     * for example
     *
     * <pre class="code">Assert.isTrue(i != 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
     *
     * @param expression a boolean expression
     * @param code
     * @param errMessage the exception message to use if the assertion fails
     * @throws BizException if expression is {@code false}
     */
    public static void isTrue(boolean expression, int code, String errMessage) {
        if (!expression) {
            throw new BizException(code, errMessage);
        }
    }

    /**
     * Assert a boolean expression, if expression is true, throwing {@code BizException}
     *
     * for example
     *
     * <pre class="code">Assert.isFalse(i == 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
     *
     * This is more intuitive than isTure.
     */
    public static void isFalse(boolean expression, int code, String errMessage) {
        if (expression) {
            throw new BizException(code, errMessage);
        }
    }

    public static void notNull(Object object, int code, String errMessage) {
        if (object == null) {
            throw new BizException(code, errMessage);
        }
    }

    public static void notEmpty(Collection<?> collection, int code, String errMessage) {
        if (collection == null || collection.isEmpty()) {
            throw new BizException(code, errMessage);
        }
    }

    public static void notEmpty(Map<?, ?> map, int code, String errMessage) {
        if (map == null || map.isEmpty()) {
            throw new BizException(code, errMessage);
        }
    }

}
