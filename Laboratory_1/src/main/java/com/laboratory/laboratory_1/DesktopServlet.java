package com.laboratory.laboratory_1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Instant;

@WebServlet(name = "DesktopServlet", value = "/DesktopServlet")
public class DesktopServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        int value = Integer.parseInt(request.getParameter("value"));
        boolean mock = request.getParameter("mock").equals("True");
        boolean sync = request.getParameter("sync").equals("True");
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if(mock) {

            out.println("Mesaj de confirmare!");
        }
        else {
            StringBuilder sb = new StringBuilder();
            Timestamp ts = Timestamp.from(Instant.now());
            for (int i = 0; i < value; i++) {
                sb.append(String.format("%s (%s) ", key, ts));
            }
            out.println(sb);
        }


    }
}
