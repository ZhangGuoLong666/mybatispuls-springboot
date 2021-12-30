package com.zgl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zgl.modules.***.mapper")
@EnableAsync(proxyTargetClass=true)
public class ZglAuthApplcation {

    public static void main(String[] args) {
        SpringApplication.run(ZglAuthApplcation.class, args);
    }


}

