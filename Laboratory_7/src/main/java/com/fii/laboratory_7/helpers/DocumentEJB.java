package com.fii.laboratory_7.helpers;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors(DocumentInterceptor.class)
public class DocumentEJB {
    public String printMessage(String message) {
        System.out.println(" Executing method : printMessage" + message);
        return "Message is "+message;
    }
}
