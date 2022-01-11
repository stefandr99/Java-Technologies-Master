package com.fii.lab9.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class ServiceReadyHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {

        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named(ServiceLiveHealthCheck.class.getSimpleName());

        responseBuilder.withData("Processors exceeded", Runtime.getRuntime().availableProcessors() <= 2);

        return responseBuilder.up().build();

    }
}
