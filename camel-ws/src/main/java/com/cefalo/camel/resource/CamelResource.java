package com.cefalo.camel.resource;

import org.apache.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author humayun
 */
@Path("notifications")
public class CamelResource {

    @POST
    @Path("/np")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public Response processNPNotification(@DefaultValue("") String commandText) throws Exception {

        System.out.println("we are here-\n" + commandText);
        boolean commandProcessed = true;

        return Response.status(commandProcessed ? HttpStatus.SC_OK : HttpStatus.SC_ACCEPTED).build();
    }

    @GET
    @Path("/test")
    public Response testMethod() {
        System.out.println("Yes we are here.");
        return Response.status(HttpStatus.SC_OK).build();
    }
}
