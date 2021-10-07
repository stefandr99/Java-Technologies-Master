package com.fii.laboratory_2.controllers;

import com.fii.laboratory_2.models.Record;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RecordController", value = "/RecordController")
public class RecordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getAttribute("action") == null || !(request.getAttribute("action")).equals("records"))
            request.setAttribute("action", "categories");

        if(request.getAttribute("action").equals("categories")) {
            RequestDispatcher rd = request.getRequestDispatcher("BusinessServlet");
            rd.include(request, response);
            RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/input.jsp");
            rd2.forward(request, response);
        }
        else {
            RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/result.jsp");
            rd2.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String key = request.getParameter("key");
        String value = request.getParameter("value");

        Record record = new Record(category, key, value);

        request.setAttribute("record", record);
        RequestDispatcher rd = request.getRequestDispatcher("BusinessServlet");
        rd.include(request,response);

        request.setAttribute("action", "records");
        doGet(request, response);
    }
}
