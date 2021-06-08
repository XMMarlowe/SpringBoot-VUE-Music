package com.marlowe.music.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: music
 * @description: 允许跨域访问配置类
 * @author: Marlowe
 * @create: 2021-06-08 09:14
 **/
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] origins = {"http://localhost:8081"};
        registry.addMapping("/**")
                .allowedOrigins(origins)
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}

