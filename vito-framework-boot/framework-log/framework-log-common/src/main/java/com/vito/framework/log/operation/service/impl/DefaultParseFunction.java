package com.vito.framework.log.operation.service.impl;

import com.vito.framework.log.operation.service.IParseFunction;

/**
 * @author muzhantong
 * create on 2021/2/6 9:58 上午
 */
public class DefaultParseFunction implements IParseFunction {

    @Override
    public boolean executeBefore() {
        return true;
    }

    @Override
    public String functionName() {
        return null;
    }

    @Override
    public String apply(Object value) {
        return null;
    }
}
