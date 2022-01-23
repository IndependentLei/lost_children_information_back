package com.lry.lostchildinfo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author : jdl
 * @description : jwt配置参数
 * @ClassName : JwtProperties
 * @create : 2022-01-22 13:07
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     *   prefix: bearer  # 请求前缀
     *   header: Authorization   # 请求头
     *   secret: whoareyou  # 盐值
     *   expire: 3600000 # 过期时间
     */
    private String prefix;
    private String header;
    private String secret;
    private Long expire;

    /**
     * 返回前缀
     * @return
     */
    public String tokenStart(){
        return this.prefix+" ";
    }
}
