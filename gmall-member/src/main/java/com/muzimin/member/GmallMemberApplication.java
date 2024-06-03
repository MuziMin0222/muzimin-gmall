package com.muzimin.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: 李煌民
 * @date: 2024-05-29 22:26
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = "com.muzimin.member.feign")
@EnableDiscoveryClient
public class GmallMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(GmallMemberApplication.class, args);
    }
}
