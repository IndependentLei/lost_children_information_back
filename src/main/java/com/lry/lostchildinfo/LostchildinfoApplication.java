package com.lry.lostchildinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// 关闭Security登录验证功能
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LostchildinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LostchildinfoApplication.class, args);
    }

}
