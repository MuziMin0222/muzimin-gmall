package com.muzimin.gmallthirdpart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GmallThirdPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallThirdPartApplication.class, args);
    }

}
