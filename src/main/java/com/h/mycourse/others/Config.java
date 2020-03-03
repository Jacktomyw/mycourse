package com.h.mycourse.others;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new LoginInterceptor())
                .addPathPatterns( "/**" ).excludePathPatterns("/", "/index","/login", "/login/check","/register","/register/check");
    }
}
