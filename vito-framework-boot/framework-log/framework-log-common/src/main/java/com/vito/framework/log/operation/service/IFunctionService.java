package com.vito.framework.log.operation.service;

/**
 * @author panjin
 */
public interface IFunctionService {

    /**
     * 执行函数
     * @param functionName 函数名称
     * @param value 函数参数
     * @return 函数执行结果
     */
    String apply(String functionName, Object value);

    /**
     * 是否在方法调用前执行
     * @param functionName 函数名称
     * @return true/false
     */
    boolean beforeFunction(String functionName);
}
