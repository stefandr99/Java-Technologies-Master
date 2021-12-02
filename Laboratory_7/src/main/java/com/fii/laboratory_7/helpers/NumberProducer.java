package com.fii.laboratory_7.helpers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.UUID;

@ApplicationScoped
public class NumberProducer {

    public NumberProducer() {
    }

    @Produces
    public String produceRegistrationNumber() {
        return UUID.randomUUID().toString();
    }
}
