package com.muzimin.product.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
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
        try {
            return new MinioClient(
                    minioEndpoint,
                    minioAccessKey,
                    minioSecretKey);
        } catch (InvalidEndpointException | InvalidPortException e) {
            throw new RuntimeException(e);
        }
    }
}
