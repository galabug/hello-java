package com.gala.bug.filter;

import com.alibaba.fastjson.JSON;
import com.gala.bug.utils.GalaUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

@WebFilter(urlPatterns = "/*",filterName = "myFilter")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("--------MyFilter init----------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        System.out.println("--------doFilter:request:"+ GalaUtils.doTranslate(req));
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("--------MyFilter destroy----------");
    }
}
