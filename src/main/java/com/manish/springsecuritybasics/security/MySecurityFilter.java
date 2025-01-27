package com.manish.springsecuritybasics.security;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * Custom security filter for logging requests.
 */
public class MySecurityFilter implements Filter {

    /**
     * Initializes the filter.
     *
     * @param filterConfig the filter configuration
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * Logs the request and response processing.
     *
     * @param servletRequest  the servlet request
     * @param servletResponse the servlet response
     * @param filterChain     the filter chain
     * @throws IOException      if an I/O error occurs
     * @throws ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Inside MySecurityFilter...");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Exiting MySecurityFilter...");
    }

    /**
     * Destroys the filter.
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}