package com.thanksto.backthanksto.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanksto.backthanksto.config.auth.PrincipalDetails;
import com.thanksto.backthanksto.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter가 있음
// /login 요청해서 username, password를 전송하면 (post)
// UsernamePasswordAuthenticationFilter이 동작을 함
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

   // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도중");

        // username, password 받기
        try {
            // System.out.println(request.getInputStream());

//            BufferedReader br = request.getReader();
//            String input = null;
//            while ((input = br.readLine()) != null){
//                System.out.println(input);
//            }

            // json 요청 기준
            ObjectMapper om = new ObjectMapper(); // json을 파싱해줌
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println("username : " +  user.getUsername());

            // token
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword()
            );

            // PrincipalDetailsService의 loadUserByUserName() 함수가 실행됨
            // authentication에는 내 로그인 정보가 담김
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("로그인 완료 : " + principalDetails.getUser().getUsername());

            // 권한 처리 때문에 session에 넣어줌
            return authentication;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //return super.attemptAuthentication(request, response);
    }

    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행됨
    // jwt 토큰을 만들어서 request 요청한 사용자에서 jwt 토큰을 response
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        String jwtToken = JWT.create()
                        .withSubject("login token")
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) // 현재 시간 + 지정시간 = 만료시간
                        .withClaim("id", principalDetails.getUser().getId())
                        .withClaim("username", principalDetails.getUser().getUsername())
                        .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        //super.successfulAuthentication(request, response, chain, authResult);
        response.addHeader("Authorization", "Bearer "+jwtToken);
    }
}
