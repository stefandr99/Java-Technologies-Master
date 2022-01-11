package com.fii.lab9.services;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class ResilientService {
    private AtomicLong counter = new AtomicLong(0);

    @CircuitBreaker(requestVolumeThreshold = 4)
    public Integer getAvailability() {
        maybeFail();
        return new Random().nextInt(30);
    }

    private void maybeFail() {
        final Long invocationNumber = counter.getAndIncrement();
        if (invocationNumber % 4 > 1) {
            throw new RuntimeException("Service failed.");
        }
    }
}
