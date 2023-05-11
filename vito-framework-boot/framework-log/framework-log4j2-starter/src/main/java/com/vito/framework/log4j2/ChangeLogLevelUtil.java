package com.vito.framework.log4j2;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

/**
 * @author panjin
 */
@Slf4j
public class ChangeLogLevelUtil {

    /**
     * 动态调整日志级别
     * @param loggerName
     * @param level
     */
    public static void changeLevel(String loggerName, String level) {
        ILoggerFactory loggerFactory = StaticLoggerBinder.getSingleton().getLoggerFactory();
        if (loggerFactory instanceof org.apache.logging.slf4j.Log4jLoggerFactory) {
            Level targetLevel = Level.getLevel(level.toUpperCase());
            if (null == targetLevel) {
                log.warn("Param level is invalid, cannot change");
                return;
            }

            if (loggerName.isBlank() || LoggerConfig.ROOT.equalsIgnoreCase(loggerName)) {
                loggerName = LogManager.ROOT_LOGGER_NAME;
            }
            // ROOT更新全部logger
            if (loggerName.isBlank()) {
                // 更新全部logger
                LoggerConfig loggerConfig = getLoggerConfig(loggerName);
                if (loggerConfig == null) {
                    loggerConfig = new LoggerConfig(loggerName, targetLevel, true);
                    getLoggerContext().getConfiguration().addLogger(loggerName, loggerConfig);
                } else {
                    loggerConfig.setLevel(targetLevel);
                }
                getLoggerContext().updateLoggers();
                log.info("change logger[ROOT] level to [{}]", targetLevel);
                return;
            }

            org.apache.logging.log4j.core.Logger logger = getLoggerContext().getLogger(loggerName);
            logger.setLevel(targetLevel);
            log.info("logger[{}] level change to [{}]", loggerName, targetLevel);
        }
    }

    private static org.apache.logging.log4j.core.LoggerContext getLoggerContext() {
        return (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
    }

    private static LoggerConfig getLoggerConfig(String name) {
        if (name.isBlank() || LoggerConfig.ROOT.equalsIgnoreCase(name)) {
            name = LogManager.ROOT_LOGGER_NAME;
        }
        return getLoggerContext().getConfiguration().getLoggers().get(name);
    }
}
