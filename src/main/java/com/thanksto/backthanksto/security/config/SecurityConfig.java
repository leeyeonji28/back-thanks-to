package com.thanksto.backthanksto.security.config;

import com.thanksto.backthanksto.security.config.jwt.JwtAuthenticationFilter;
import com.thanksto.backthanksto.security.config.jwt.JwtAuthorizationFilter;
import com.thanksto.backthanksto.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록 됨
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    private final CorsConfig corsConfig;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세션을 사용하지 않음
                .and()
                .formLogin().disable()// jwt 인증방식을 위해 id pw를 폼로그인으로 처리를 안함
                .httpBasic().disable()// http 기본 인증방식을 사용안함
                //.addFilter(new JwtAuthenticationFilter(authenticationManager())) // 이전 버전, 아래가 최신
                .apply(new MyCustomDsl())
                .and()
                .authorizeRequests(authorize -> authorize
//                        .antMatchers("/api/v1/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 해당 권한이 있는 사람만 접속 가능
//                        .antMatchers("/api/v1/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//                        .antMatchers("/api/v1/admin/**").access("hasRole('ROLE_ADMIN')")
                        .anyRequest().permitAll() // 위의 요청을 제외한 다른 요청은 전부 권한 없이 요청을 허용
                )
                .build();
    }
    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity>{
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        }
    }
}
