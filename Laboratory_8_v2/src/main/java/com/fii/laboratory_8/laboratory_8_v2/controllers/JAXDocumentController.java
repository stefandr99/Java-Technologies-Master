package com.fii.laboratory_8.laboratory_8_v2.controllers;

import com.fii.laboratory_8.laboratory_8_v2.entities.Document;
import com.fii.laboratory_8.laboratory_8_v2.repositories.DocumentRepository;
import com.fii.laboratory_8.laboratory_8_v2.repositories.UserRepository;
import com.fii.laboratory_8.laboratory_8_v2.services.GraphService;

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

    @Inject
    GraphService graphService;

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

    @GET
    @Path("/checkCircuit")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean allWithCheck(@DefaultValue("-1") @QueryParam("userId") int userId) {
        List<Document> documents;

        if(userId == -1)
            documents = documentRepository.get();
        else
            documents = documentRepository.getByUserId(userId);

        boolean isCircuit = graphService.check(documents);

        return isCircuit;
    }

/*
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public boolean create(@FormDataParam("name") String name,
                           @FormDataParam("registrationNumber") String registrationNumber,
                           @FormDataParam("username") String username,
                           @FormDataParam("file") InputStream uploadedInputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        for (int i; (i = uploadedInputStream.read(buffer)) > 0;){
            byteArrayOutputStream.write(buffer, 0, i);
        }
        User user = userRepository.getByUsername(username);
        Document2 doc = new Document2(name, byteArrayOutputStream.toByteArray(), user, "1234");
        documentRepository.create2(doc);

        return true;
    }*/
/*
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {
        String fileLocation = "e://" + fileDetail.getFileName();
        //saving file
        try {
            FileOutputStream out = new FileOutputStream(new File(fileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            out = new FileOutputStream(new File(fileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {e.printStackTrace();}
        String output = "File successfully uploaded to : " + fileLocation;
        return Response.status(200).entity(output).build();
    }
*/
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
