package com.vito.framework.s3.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.vito.framework.s3.properties.S3Properties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 * @author panjin
 */
@ConditionalOnClass(AmazonS3.class)
public class S3InitService implements InitializingBean {

    @Autowired
    private S3Properties s3Properties;

    private AmazonS3 s3client;

    @Override
    public void afterPropertiesSet() {
        ClientConfiguration config = new ClientConfiguration();
        AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(s3Properties.getEndpoint(), s3Properties.getRegion());
        AWSCredentials credentials = new BasicAWSCredentials(s3Properties.getAccessKey(), s3Properties.getSecretKey());
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(credentials);
        this.s3client = AmazonS3Client.builder()
                .withEndpointConfiguration(endpoint)
                .withClientConfiguration(config)
                .withCredentials(awsCredentialsProvider)
                .withPathStyleAccessEnabled(s3Properties.getPathStyleAccessEnabled())
                .disableChunkedEncoding()
                .build();

        // 根据配置创建bucket
        if (StringUtils.isNotBlank(s3Properties.getBucketName()) && !s3client.doesBucketExistV2(s3Properties.getBucketName())) {
            s3client.createBucket(s3Properties.getBucketName());
        }
    }

    public AmazonS3 getS3client() {
        return s3client;
    }
}
