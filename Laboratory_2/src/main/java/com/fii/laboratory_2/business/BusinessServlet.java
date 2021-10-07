package com.fii.laboratory_2.business;

import com.fii.laboratory_2.models.Record;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "BusinessServlet", value = "/BusinessServlet")
public class BusinessServlet extends HttpServlet {

    private List<Record> records = new ArrayList<>();
    private final List<String> categories = new ArrayList<>(Arrays.asList("Music", "Nature", "Sport", "Technology", "Fashion"));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", categories);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Record record = (Record) request.getAttribute("record");
        records.add(record);
        request.setAttribute("records", records);
    }
}
