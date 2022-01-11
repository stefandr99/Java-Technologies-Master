package com.fii.lab9.resources;

import com.fii.lab9.entities.Document;
import com.fii.lab9.repositories.DocumentRepository;
import com.fii.lab9.repositories.UserRepository;
import org.eclipse.microprofile.metrics.*;
import org.eclipse.microprofile.metrics.annotation.RegistryType;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/document")
public class JAXDocumentController {
    @Inject
    DocumentRepository documentRepository;

    @Inject
    UserRepository userRepository;

    /*@Inject
    @RegistryType(type = MetricRegistry.Type.APPLICATION)
    private MetricRegistry metricRegistry;

    Metadata geDocsCounterMetadata = new Metadata(
            "getDocuments",
            "getDocuments",
            "Number of documents call",
            MetricType.COUNTER,
            MetricUnits.NONE);

    Counter getDocsCounter = metricRegistry.counter(geDocsCounterMetadata);*/

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(@DefaultValue("-1") @QueryParam("userId") int userId) {
        List<Document> documents;

        if(userId == -1)
            documents = documentRepository.get();
        else
            documents = documentRepository.getByUserId(userId);

        Response response = Response.ok().entity(documents).build();

        // getDocsCounter.inc();

        return response;
    }

    @DELETE
    public Response delete(@QueryParam("id") int id) {
        if (documentRepository.delete(id))
            return Response.status(Response.Status.OK).build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@FormParam("id") int id, @FormParam("name") String name,
                           @FormParam("registrationNumber") String registrationNumber) {
        if (documentRepository.update(id, name, registrationNumber))
            return Response.status(Response.Status.OK).build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }
}
