package com.fii.laboratory_8.laboratory_8_v2;

import com.fii.laboratory_8.laboratory_8_v2.entities.Document2;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Provider
public class CacheFilter implements ContainerResponseFilter {
    private Map<Integer, List<Document2>> documents = new HashMap<>();

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        if(containerRequestContext.getMethod().equals("GET")){
            String id = containerRequestContext.getUriInfo().getQueryParameters().containsKey("userId") ?
                    containerRequestContext.getUriInfo().getQueryParameters().get("userId").get(0) : "-1";

            Integer integerId = Integer.parseInt(id);
            if (documents.containsKey(integerId)) {
                containerResponseContext.setEntity(documents.get(integerId));
            }
            else {
                documents.put(integerId, (List<Document2>) containerResponseContext.getEntity());
            }
        }
    }
}
