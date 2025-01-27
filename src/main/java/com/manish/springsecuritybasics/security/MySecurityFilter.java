package com.manish.springsecuritybasics.security;

import jakarta.servlet.*;

import java.io.IOException;

public class MySecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Inside MySecurityFilter...");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Exiting MySecurityFilter...");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
