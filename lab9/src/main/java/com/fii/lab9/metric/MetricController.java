package com.fii.lab9.metric;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.RegistryType;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Random;

@Path("/metric")
@ApplicationScoped
public class MetricController {

    @Inject
    @RegistryType(type = MetricRegistry.Type.APPLICATION)
    private MetricRegistry metricRegistry;

    private Counter counter;

    @Path("counted")
    @Timed(name = "timed-request")
    @GET
    public Response timedRequest() {
        /*int wait = new Random().nextInt(1000);
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return Response.ok(metricRegistry.counter("getDocuments").getCount()).build();
    }


    @Path("increment")
    @GET
    public long doIncrement() {
        counter.inc();
        return counter.getCount();
    }

    @Gauge(name = "counter_gauge", unit = MetricUnits.NONE)
    private long getCustomerCount() {
        return counter.getCount();
    }
}
