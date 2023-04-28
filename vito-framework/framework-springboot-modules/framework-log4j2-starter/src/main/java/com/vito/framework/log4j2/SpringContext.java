package com.vito.framework.log4j2;

import org.springframework.core.env.Environment;

/**
 * @author panjin
 */
public class SpringContext {

    private static Environment environment;

    protected static void setEnvironment(Environment environment) {
        SpringContext.environment = environment;
    }

    protected static Environment getEnvironment() {
        return SpringContext.environment;
    }
}
