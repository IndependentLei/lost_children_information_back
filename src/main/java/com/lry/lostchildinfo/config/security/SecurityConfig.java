package com.lry.lostchildinfo.config.security;

import com.lry.lostchildinfo.config.security.filter.JwtAuthenticationTokenFilter;
import com.lry.lostchildinfo.config.security.handle.*;
import com.lry.lostchildinfo.config.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @author : jdl
 * @description : security配置
 * @ClassName : SecurityConfig
 * @create : 2022-01-22 12:40
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    // 用户登录验证
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // 权限不足管理器
    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler;

    // 失败管理器
    @Autowired
    AuthenticationEntryPointHandler authenticationEntryPointHandler;

    // 登录失败管理器
    @Autowired
    LoginFailureHandle loginFailureHandle;

    // 登录成功管理器
    @Autowired
    LoginSuccessHandle loginSuccessHandle;

    // 退出成功管理器
    LogoutSuccessHandlerImpl logoutSuccessHandler;

    //jwt过滤器,重写过滤器
    @Bean
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        return new JwtAuthenticationTokenFilter(authenticationManager());
    }

    // 密码加密方式
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // 不需要认证的白名单
    private static final String[] URL_WHITELIST ={
            "/lostchildinfo/user/**",
            "/lostchildinfo/childrenInfo/**",
            "/lostchildinfo/childrenInfoAttach/**",
            "/lostchildinfo/sonComment/**",
            "/lostchildinfo/fatherComment/**",
            "/lostchildinfo/slideshow/**",
            "/lostchildinfo/frontHome/**",
            "/lostchildinfo/homePage/**",
            "/lostchildinfo/role/**",
            "/lostchildinfo/userrole/**",
            "/lostchildinfo/log/**",
            "/lostchildinfo/menu/**",
            "/common/uploadPic",
            "/login", // 登录
            "/logout/*", // 退出成功
            // swagger
            "/swagger-ui.html",
            // druid页面
            "/druid/index.html",
            "/favicon.ico",
            "/common/*",
            // 放行静态文件
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
        .and()
                .csrf()
                .disable()
        //登录配置
        .formLogin()
                .successHandler(loginSuccessHandle)
                .failureHandler(loginFailureHandle)
        // 退出成功处理器配置
        .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/success")
//                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
        //禁用session
        .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //配置拦截规则
        .and()
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()
                .anyRequest().authenticated()
        //异常处理器
        .and()
        .exceptionHandling()
         // 认证失败管理器
        .authenticationEntryPoint(authenticationEntryPointHandler)
         // 权限不足管理器
        .accessDeniedHandler(accessDeniedHandler)

        // 配置自定义的过滤器
        .and()
        .addFilter(jwtAuthenticationTokenFilter());
    }

//    // 将重写的UserDetailsServiceImpl注入到管理器里面
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
