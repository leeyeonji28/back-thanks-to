package com.thanksto.backthanksto.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**") // 해당 하위 주소는 다 허용하겠다. /api/**
                .allowedOrigins("*") // 모든 ip에 응답을 허용
                .allowedMethods("*") // 모든 post, get, put, delete, patch 요청 허용
                .allowedHeaders("*"); // 모든 header에 응답을 허용
    }

//    String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/"; // 저장 경로
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("classpath:/static/");
//    }
}
