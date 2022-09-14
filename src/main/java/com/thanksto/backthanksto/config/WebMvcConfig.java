package com.thanksto.backthanksto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**") // 해당 하위 주소는 다 허용하겠다.
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
