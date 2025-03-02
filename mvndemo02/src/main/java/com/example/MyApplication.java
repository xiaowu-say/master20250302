package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author:zhanghongwu
 * Date:2025/2/27
 */
@SpringBootApplication // 核心注解
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args); // 启动 Spring Boot
    }
}
