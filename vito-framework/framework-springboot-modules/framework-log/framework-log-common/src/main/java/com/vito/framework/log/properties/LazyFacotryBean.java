package com.vito.framework.log.properties;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author panjin
 */
public class LazyFacotryBean implements FactoryBean<Object> {

    private String className;

    public LazyFacotryBean(String className) {
        this.className = className;
    }

    @Override
    public Object getObject() throws Exception {
        Class<?> clazz = Class.forName(className);
        return clazz.getDeclaredConstructor().newInstance();
    }

    @Override
    public Class<?> getObjectType() {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
