package com.vito.framework.s3.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author panjin
 */
@Getter
@Setter
@ConfigurationProperties(prefix = S3Properties.PREFIX)
public class S3Properties {

    public static final String PREFIX = "s3";

    /**
     * 用户名
     */
    private String accessKey;
    /**
     * 密码
     */
    private String secretKey;
    /**
     * 访问端点
     */
    private String endpoint;
    /**
     * bucket名称
     */
    private String bucketName;
    /**
     * 区域
     */
    private String region;
    /**
     * path-style
     */
    private Boolean pathStyleAccessEnabled = true;
}
