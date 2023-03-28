package com.vito.framework.exception;

/**
 *
 * 说明：需要在类中实现setArgs方法，并返回当前类对象
 * 示例：BankErrorCodeEnum枚举类
 * <pre>
 * public BankErrorCodeEnum setArgs(Object[] args) {
 *     this.args = args;
 *     return this;
 * }
 * </pre>
 * @author panjin
 */
public interface BaseCode {

    /**
     * Returns the error code
     * @return
     */
    String getErrorCode();

    /**
     * Returns the error message
     * @return
     */
    String getErrorMessage();

    /**
     * Returns the error message of the arguments
     * @return
     */
    Object[] getArgs();

}
