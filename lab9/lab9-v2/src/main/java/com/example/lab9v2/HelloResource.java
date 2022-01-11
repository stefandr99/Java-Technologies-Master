package com.example.lab9v2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/document")
public class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        Client client = ClientBuilder.newClient();

        WebTarget resource = client.target("http://en1210237.endava.net:8080/lab9-v1/resources/document");

        Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);

        Response response = request.get();

        return response;
    }
}