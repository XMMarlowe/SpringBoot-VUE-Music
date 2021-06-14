package com.marlowe.music.config;

import com.marlowe.music.interceptors.JWTInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: music
 * @description: 拦截器配置
 * @author: Marlowe
 * @create: 2021-06-11 22:29
 **/
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                // 所有用户接口都放行
                .excludePathPatterns("/user/**");
    }
}
