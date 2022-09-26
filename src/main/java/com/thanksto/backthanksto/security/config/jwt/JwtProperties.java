package com.thanksto.backthanksto.security.config.jwt;

public interface JwtProperties {
    String SECRET = "MySecretKey1$1$1234";
    int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 14; // 14일짜리 토큰
    String TOKEN_PRETIX = "Bearer ";
}
