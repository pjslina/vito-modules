package com.vito.framework.log.operation.service;

/**
 * @author panjin
 */
public interface IParseFunction {

    /**
     * 是否在执行方法之前执行
     * @return true 执行方法之前执行，false 执行方法之后执行
     */
    default boolean executeBefore() {
        return false;
    }

    /**
     * 函数名称
     * @return 函数名称
     */
    String functionName();

    /**
     * 函数执行
     * @param value 函数入参
     * @return 文案
     * @since 1.1.0 参数从String 修改为Object类型，可以处理更多的场景，可以通过SpEL表达式传递对象了
     * 老版本需要改下自定义函数的声明，实现使用中把 用到 value的地方修改为 value.toString 就可以兼容了
     */
    String apply(Object value);
}
