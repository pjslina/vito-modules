package com.vito.framework.common.configuration;

import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;
import com.vito.framework.common.utils.MapStructOfGrpcUtil;
import com.vito.framework.common.utils.MapStructUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * @author panjin
 */
public class CommonAutoConfiguration {

    @Bean
    public MapStructUtil mapStructUtil() {
        return new MapStructUtil();
    }

    @Bean
    @ConditionalOnClass({Timestamp.class, Timestamps.class})
    public MapStructOfGrpcUtil mapStructOfGrpcUtil() {
        return new MapStructOfGrpcUtil();
    }
}
