package com.laboratory_9.laboratory_9.controllers;

import com.laboratory_9.laboratory_9.entities.Document;
import com.laboratory_9.laboratory_9.repositories.DocumentRepository;
import com.laboratory_9.laboratory_9.repositories.UserRepository;

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


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Document> all(@DefaultValue("-1") @QueryParam("userId") int userId) {
        List<Document> documents;

        if(userId == -1)
            documents = documentRepository.get();
        else
            documents = documentRepository.getByUserId(userId);

        return documents;
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
