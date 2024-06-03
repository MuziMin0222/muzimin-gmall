package com.muzimin.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: 李煌民
 * @date: 2024-05-29 22:35
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class GmallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(GmallOrderApplication.class, args);
    }
}
