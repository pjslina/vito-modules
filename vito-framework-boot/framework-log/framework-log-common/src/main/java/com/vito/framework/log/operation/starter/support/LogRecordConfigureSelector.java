package com.vito.framework.log.operation.starter.support;

import com.vito.framework.log.operation.starter.annotation.EnableLogRecord;
import com.vito.framework.log.operation.starter.configuration.LogRecordProxyAutoConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.lang.Nullable;

/**
 * DATE 6:57 PM
 *
 * @author mzt.
 */
public class LogRecordConfigureSelector extends AdviceModeImportSelector<EnableLogRecord> {

    @Override
    @Nullable
    public String[] selectImports(AdviceMode adviceMode) {
        return switch (adviceMode) {
            case PROXY ->
                    new String[]{AutoProxyRegistrar.class.getName(), LogRecordProxyAutoConfiguration.class.getName()};
            case ASPECTJ -> new String[]{LogRecordProxyAutoConfiguration.class.getName()};
            default -> null;
        };
    }
}