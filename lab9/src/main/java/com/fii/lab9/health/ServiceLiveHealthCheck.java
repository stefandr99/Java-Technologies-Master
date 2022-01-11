package com.fii.lab9.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class ServiceLiveHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {

        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named(ServiceLiveHealthCheck.class.getSimpleName());

        responseBuilder.withData("Memory almost full", Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() < 10000);

        return responseBuilder.up().build();
    }
}
