package com.vito.framework.log4j2;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.AbstractLookup;
import org.apache.logging.log4j.core.lookup.StrLookup;

/**
 * @author panjin
 */
@Plugin(name = "spring", category = StrLookup.CATEGORY)
public class SpringEnvironmentLookup extends AbstractLookup {

    @Override
    public String lookup(LogEvent event, String key) {
        if (SpringContext.getEnvironment() != null) {
            return SpringContext.getEnvironment().getProperty(key);
        }
        return null;
    }

}
