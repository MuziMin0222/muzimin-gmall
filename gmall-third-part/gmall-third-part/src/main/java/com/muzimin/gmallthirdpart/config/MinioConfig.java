package com.muzimin.gmallthirdpart.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 李煌民
 * @date: 2024-07-14 21:08
 **/
@Configuration
public class MinioConfig {
    @Value("${minio.endpoint}")
    private String minioEndpoint;

    @Value("${minio.access-key}")
    private String minioAccessKey;

    @Value("${minio.secret-key}")
    private String minioSecretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient
                .builder()
                .endpoint(minioEndpoint)
                .credentials(minioAccessKey, minioSecretKey)
                .build();
    }

    /*@Bean
    public AmazonS3 minioClient() {
        ClientConfiguration config = new ClientConfiguration();
        config.setProtocol(Protocol.HTTP);
        config.disableSocketProxy();
        // 初始化S3上传操作类
        BasicAWSCredentials credentials = new BasicAWSCredentials(minioAccessKey, minioSecretKey);
        AmazonS3 client = AmazonS3ClientBuilder
                .standard()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                //.withRegion(minioEndpoint) // Regions.CN_NORTH_1.getName()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(minioEndpoint, ""))
                .enablePathStyleAccess()
                .build();

        return client;
    }*/
}
