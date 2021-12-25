package com.zgl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zgl.mapper")
public class ZglAuthApplcation {

    public static void main(String[] args) {
        SpringApplication.run(ZglAuthApplcation.class, args);
    }


}

