package com.lry.lostchildinfo.config.swagger;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : jdl
 * @description : swagger配置
 * @ClassName : SwaggerConfig
 * @create : 2022-01-21 16:44
 */
@Configurable
@EnableSwagger2 //声明启动swagger2
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lry.lostchildinfo.controller"))//扫描的包路径
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("儿童丢失信息平台")//文档说明
                .version("1.0.0")//文档版本说明
                .build();
    }
}
