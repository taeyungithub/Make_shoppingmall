package com.nhnacademy.shoppingmall.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import java.io.IOException;


@WebFilter(
        filterName = "characterEncodingFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8")
        }
)

public class CharacterEncodingFilter implements Filter {
    //todo#8 UTF-8 인코딩, initParams의 encoding parameter value값을 charset 으로 지정합니다.
    //@WebFilter(initParams = {@WebInitParam(name = "encoding",value = "UTF-8")})
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(this.encoding);
        chain.doFilter(servletRequest, servletResponse);
    }
}