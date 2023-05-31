package com.vito.framework.s3.config;

import com.vito.framework.s3.properties.S3Properties;
import com.vito.framework.s3.service.S3InitService;
import com.vito.framework.s3.service.S3Service;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author panjin
 */
@EnableConfigurationProperties(S3Properties.class)
@Import({S3InitService.class, S3Service.class})
public class S3AutoConfiguration {

}
