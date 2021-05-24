package com.marlowe.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


/**
 * @program: SpringBoot-VUE-Music
 * @description: Swagger在线文档配置类
 * @author: Marlowe
 * @create: 2021-05-24 18:46
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 配置了Swagger的Docket的bean实例
     *
     * @return
     */
    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Marlowe")
                // enable是否启动Swagger，如果为false，则swagger不能在浏览器中访问
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.marlowe.music.controller"))
                .build();
    }

    public ApiInfo apiInfo() {

        // 作者信息
        Contact contact = new Contact("Marlowe", "https://xmmarlowe.github.io", "marlowe246@qq.com");

        return new ApiInfo("SpringBoot-VUE-Music API Documentation",
                "Api Documentation",
                "v1.0", "urn:tos",
                contact, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }
}