package com.fii.lab9.resilient;

import com.fii.lab9.entities.Document;
import com.fii.lab9.repositories.DocumentRepository;
import com.fii.lab9.services.ResilientService;
import org.eclipse.microprofile.faulttolerance.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/resilience")
@ApplicationScoped
public class ResilienceController {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ResilienceController.class));

    private AtomicLong counter = new AtomicLong(0);

    @Inject
    DocumentRepository documentRepository;

    @Inject
    ResilientService resilientService;

    @GET
    @Path("/docs")
    @Retry(maxRetries = 2)
    @Timeout(250)
    @Fallback(fallbackMethod = "fallback")
    public Response documents() {
        final long invocationNumber = counter.getAndIncrement();

        try {
            randomDelay();
            LOGGER.log(Level.ALL, "CoffeeResource#recommendations() invocation #%d returning successfully", invocationNumber);
            List<Document> documents = documentRepository.get();
            return Response.ok().entity(documents).build();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ALL, "CoffeeResource#recommendations() invocation #%d timed out after %d ms",
                    invocationNumber);
            return null;
        }
    }

    private void randomDelay() throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
    }

    public Response fallback() {
        Document document = documentRepository.getFirst();
        return Response.ok().entity(document).build();
    }

    @Path("/availability")
    @GET
    public Response availability() {
        final Long invocationNumber = counter.getAndIncrement();

        Document document = documentRepository.getFirst();

        if (document == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            Integer availability = resilientService.getAvailability();
            LOGGER.log(Level.FINE, "Documents#availability() invocation #%d returning successfully", invocationNumber);
            return Response.ok(availability).build();
        } catch (RuntimeException e) {
            String message = e.getClass().getSimpleName() + ": " + e.getMessage();
            LOGGER.log(Level.SEVERE, "Documents#availability() invocation #%d failed", invocationNumber);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .build();
        }
    }

    @GET
    @Path("/bulkhead")
    @Asynchronous
    @Bulkhead(value = 1, waitingTaskQueue = 1)
    public CompletionStage<Document> bulkhead() throws InterruptedException {
        Thread.sleep(3000);
        Document document = documentRepository.getFirst();
        return CompletableFuture.completedFuture(document);
    }
}
