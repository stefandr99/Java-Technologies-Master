package com.fii.laboratory_2.filters;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseFilter extends HttpServletResponseWrapper {
    private final StringWriter output;

    public ResponseFilter(HttpServletResponse response) {
        super(response);
        output = new StringWriter();
    }

    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(output);
    }

    @Override
    public String toString() {
        return output.toString();
    }
}
