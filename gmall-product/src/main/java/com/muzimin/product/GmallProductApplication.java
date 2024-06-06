package com.muzimin.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: 李煌民
 * @date: 2024-05-29 22:46
 **/
@SpringBootApplication
@MapperScan("com.muzimin.product.dao")
@EnableDiscoveryClient
public class GmallProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(GmallProductApplication.class, args);
    }
}
