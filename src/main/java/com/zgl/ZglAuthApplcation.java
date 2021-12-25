package com.zgl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication

@MapperScan("com.zgl.mapper")
public class ZglAuthApplcation {

    public static void main(String[] args) {
        SpringApplication.run(ZglAuthApplcation.class, args);
    }


}

