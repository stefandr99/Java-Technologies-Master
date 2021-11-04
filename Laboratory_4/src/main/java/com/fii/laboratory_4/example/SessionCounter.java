package com.fii.laboratory_4.example;

import javax.faces.bean.ManagedBean;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@ManagedBean(name = "sessionCounter")
@WebListener
public class SessionCounter implements HttpSessionListener
{
    final private AtomicInteger sessionCount = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        sessionCount.incrementAndGet();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessionCount.decrementAndGet();
    }

    public int getTotalSessionCount() {
        return sessionCount.get();
    }
}