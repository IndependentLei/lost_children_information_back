package com.lry.lostchildinfo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : jdl
 * @description : ftp对象
 * @ClassName : FtpProperties
 * @create : 2022-01-25 0:20
 */
@Data
@Component
@ConfigurationProperties(prefix = "ftp")
public class FtpProperties {
//    address: file.7b114.xyz # 路径
//    port: 21 # 端口
//    username: ftpuser # linux的用户名
//    password: ftpuser # linux的密码
//    basePath: /usr/local/my_project/first_demo/image/blog_avater #上传路径
//    imageBaseUrl: https://file.7b114.xyz/blog_avater # 图片访问根路径
    private String address;
    private Integer port;
    private String username;
    private String password;
    private String basePath;
    private String imageBaseUrl;
}
