package com.fii.laboratory_7.helpers;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class DocumentInterceptor {
    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        System.out.println("DocumentInterceptor - Logging BEFORE calling method :"+context.getMethod().getName() );

        Object result = context.proceed();

        System.out.println("DocumentInterceptor - Logging AFTER calling method :"+context.getMethod().getName() );

        return result;
    }
}
