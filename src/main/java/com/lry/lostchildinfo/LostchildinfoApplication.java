package com.lry.lostchildinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

// 关闭Security登录验证功能
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LostchildinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LostchildinfoApplication.class, args);
    }

//    @Bean
//    public WebSecurityConfigurerAdapter loginPageConfig(){
//        return new WebSecurityConfigurerAdapter() {
//            @Override
//            public void configure(HttpSecurity httpSecurity) throws Exception {
//                httpSecurity.removeConfigurer(DefaultLoginPageConfigurer.class); //将默认加载的登录页配置删除
//            }
//        };
//    }
}
