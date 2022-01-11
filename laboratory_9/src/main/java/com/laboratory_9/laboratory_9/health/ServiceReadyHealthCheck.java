package com.laboratory_9.laboratory_9.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class ServiceReadyHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {

        return HealthCheckResponse.named(ServiceReadyHealthCheck.class.getSimpleName()).withData("ready",true).up().build();

    }
}
