package com.thanksto.backthanksto.filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SecurityFilter implements Filter {
    //    필수 메소드
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter1");
        chain.doFilter(request, response);

    // PrintWriter out = response.getWriter();
    // out.print("hi");
    }
}
