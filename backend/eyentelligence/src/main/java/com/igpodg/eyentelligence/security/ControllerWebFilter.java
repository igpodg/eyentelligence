package com.igpodg.eyentelligence.security;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class ControllerWebFilter implements Filter {
    @Value("${server.port}")
    private int currentServerPort;

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException
    {
        this.filterConfig = filterConfig;
        this.filterConfig.getServletContext().log("ControllerWebFilter initialized...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        //httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, " +
                "Content-Type, Accept, X-API-Key");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, " +
                "POST, PUT, DELETE");
        String securityPolicy = "default-src 'self'; child-src 'none'; object-src 'none'; " +
                "style-src 'self' 'unsafe-inline'; font-src data:; frame-src 'self'; " +
                "connect-src 'self' https://localhost:" + this.currentServerPort +
                "; script-src 'self' 'unsafe-inline' 'unsafe-eval';";
        httpServletResponse.setHeader("Content-Security-Policy", securityPolicy);
        httpServletResponse.setHeader("X-Content-Security-Policy", securityPolicy);
        httpServletResponse.setHeader("Frame-Options", "SAMEORIGIN");
        httpServletResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
        httpServletResponse.setHeader("X-Content-Type-Options", "nosniff");
        httpServletResponse.setHeader("Strict-Transport-Security", "max-age=15768000");

        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
        this.filterConfig.getServletContext().log("ControllerWebFilter destroyed...");
    }
}
