package com.mark.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/7/8.
 */
//@WebFilter(urlPatterns = "/*")
public class CharestFilter implements Filter{
    private String charSet;
    public void init(FilterConfig filterConfig) throws ServletException {
        charSet = filterConfig.getInitParameter("charSet");
        if(charSet==null&&charSet.equals(""))
            charSet = "UTF-8";
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request,response);
    }

    public void destroy() {

    }
}
