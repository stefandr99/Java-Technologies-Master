package com.fii.laboratory_2.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class CategoryListener implements HttpSessionListener {
    private static String category = "";

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        category = httpSessionEvent.getSession().getServletContext().getInitParameter("default_category");
    }

    public static String getDefaultCategory() {
        return category;
    }
}
