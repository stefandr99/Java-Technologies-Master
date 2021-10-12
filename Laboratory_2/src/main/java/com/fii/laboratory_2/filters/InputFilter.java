package com.fii.laboratory_2.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebFilter(urlPatterns = {"/"})
public class InputFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);

        String ipAddress = request.getRemoteAddr();
        System.out.println("IP: " + ipAddress + ", Time: " + new Date());
    }
}
