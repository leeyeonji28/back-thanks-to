package com.thanksto.backthanksto.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    //    필수 메소드
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 토큰 : MySecretKey1$1$1234
        // id, pw가 정상적으로 들어와 로그인이 완료되면 토큰을 만들어주고 그걸 응답한다.
        // 요청할 때마다 header에 Authorization에 value값으로 토큰을 가지고 오는데
        // 그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지 검증한다.
//        if (req.getMethod().equals("POST")) {
//            System.out.println("POST 요청됨");
//            String headerAuth = req.getHeader("Authorization");
//            System.out.println(headerAuth);
//
//            if (headerAuth.equals("MySecretKey1$1$1234")) {
//                chain.doFilter(req, res); // 끝내지 말고 계속 진행하기 위해 추가
//            } else {
//                PrintWriter out = res.getWriter();
//                out.println("not");
//            }
//        }
        chain.doFilter(req, res);



    }
}
