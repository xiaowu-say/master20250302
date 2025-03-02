package com.example.testdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Author:zhanghongwu
 * Date:2025/2/27
 */
@SpringBootApplication // 核心注解
@ComponentScan(basePackages = {"com.example.testdemo", "com.example.utils"})
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.example.testdemo.DemoApplication.class, args); // 启动 Spring Boot
    }
}
