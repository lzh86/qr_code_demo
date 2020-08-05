package com.demo.interceptor;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println(request.getHeader("Access-Control-Request-Method"));
        String header = request.getHeader("Access-Control-Request-Method");
        System.out.println(header);
        String header1 = request.getHeader("Access-Control-Request-Headers");
        System.out.println(header1);
        //if( request.getHeader("Access-Control-Request-Method") != null && request.getHeader("Access-Control-Request-Headers") != null ){
            System.out.println("进入测试");
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Methods",request.getHeader("Access-Control-Request-Method"));
            response.setHeader("Access-Control-Allow-Headers",request.getHeader("Access-Control-Request-Headers"));
            response.setHeader("Access-Control-Max-Age","300");
            response.setHeader("Access-Control-Allow-Credentials","true");
        //}
        filterChain.doFilter(request,response);
    }
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
