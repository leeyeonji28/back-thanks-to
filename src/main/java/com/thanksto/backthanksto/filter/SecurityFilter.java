package com.thanksto.backthanksto.filter;

import javax.servlet.*;
import java.io.IOException;

public class SecurityFilter implements Filter {
    //    필수 메소드
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter1");
        chain.doFilter(request, response); // 끝내지 말고 계속 진행하기 위해 추가

    // PrintWriter out = response.getWriter();
    // out.print("hi");
    }
}
