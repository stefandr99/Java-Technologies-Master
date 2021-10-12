package com.fii.laboratory_2.decorators;

import com.fii.laboratory_2.filters.ResponseFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "ResponseDecorator", urlPatterns = {"/RecordController"})
public class ResponseDecorator implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        ResponseFilter wrapper
                = new ResponseFilter((HttpServletResponse) response);

        chain.doFilter(request, wrapper);

        String content = wrapper.toString();

        content = "<h1>Records table</h1>" + content;
        content += "<footer style='min-height:50px;'><p>Author: Stefan Dragoi</p></footer>";

        PrintWriter out = response.getWriter();
        out.write(content);
    }
}
