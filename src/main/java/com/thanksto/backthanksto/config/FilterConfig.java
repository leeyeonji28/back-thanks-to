package com.thanksto.backthanksto.config;

import com.thanksto.backthanksto.filter.SecurityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<SecurityFilter> filter1(){
        FilterRegistrationBean<SecurityFilter> bean = new FilterRegistrationBean<>(new SecurityFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 낮은 번호가 필터 중에서 가장 먼저 실행 됨
        return bean;
    }
}
