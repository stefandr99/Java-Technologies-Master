package com.fii.laboratory_7.controllers;

import com.fii.laboratory_7.entities.Document2;
import com.fii.laboratory_7.entities.User;
import com.fii.laboratory_7.helpers.NumberProducer;
import com.fii.laboratory_7.repositories.DocumentRepository;
import com.fii.laboratory_7.repositories.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Path("/document")
public class JAXDocumentController {
    @Inject
    DocumentRepository documentRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    NumberProducer numberProducer;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Document2> all(@DefaultValue("-1") @QueryParam("userId") int userId) {
        List<Document2> documents;

        if(userId == -1)
            documents = documentRepository.get();
        else
            documents = documentRepository.getByUserId(userId);

        return documents;
    }

    /*@POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response create(@FormDataParam("name") String name,
                           @FormDataParam("registrationNumber") String registrationNumber,
                           @FormDataParam("registrationNumber") String username,
                           @FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        for (int i; (i = uploadedInputStream.read(buffer)) > 0;){
            byteArrayOutputStream.write(buffer, 0, i);
        }
        User user = userRepository.getByUsername(username);
        Document2 doc = new Document2(name, byteArrayOutputStream.toByteArray(), user, numberProducer.produceRegistrationNumber());
        documentRepository.create2(doc);

        return Response.status(Response.Status.OK).build();
    }*/

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
