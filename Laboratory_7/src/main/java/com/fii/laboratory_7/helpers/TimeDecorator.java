package com.fii.laboratory_7.helpers;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.time.LocalTime;

@Decorator
public class TimeDecorator implements CheckInterval {
    @Inject
    @Delegate
    @Any
    CheckInterval checkInterval;

    @Override
    public boolean checkInterval() {
        LocalTime start = LocalTime.parse( "09:00:00" );
        LocalTime stop = LocalTime.parse( "22:00:00" );
        LocalTime now = LocalTime.now();

        return (now.isAfter(start) && now.isBefore(stop));
    }
}
