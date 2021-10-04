package com.laboratory.laboratory_1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
        boolean mock = request.getParameter("mock") != null;
        boolean sync = request.getParameter("sync") != null;

        if(mock) {
            PrintWriter out = response.getWriter();
            out.println("Mesaj de confirmare");
        }
        else {
            StringBuilder sb = new StringBuilder();
            Timestamp ts = Timestamp.from(Instant.now());
            for (int i = 0; i < value; i++) {
                sb.append(String.format("%s (%s) ", key, ts));
            }
            PrintWriter out = response.getWriter();
            out.println(sb);
        }


    }
}
