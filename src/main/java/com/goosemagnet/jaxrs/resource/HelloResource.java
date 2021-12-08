package com.goosemagnet.jaxrs.resource;

import com.goosemagnet.jaxrs.model.User;
import com.goosemagnet.jaxrs.model.dto.HelloDto;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

import java.time.Instant;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    private static int visits = 0;

    @Context
    private User user;

    @GET
    @Path("/user")
    @RolesAllowed({"USER", "ADMIN"})
    public HelloDto helloUser(@QueryParam("visit") boolean visit) {
        return greet(visit);
    }

    @GET
    @Path("/admin")
    @RolesAllowed({"ADMIN"})
    public HelloDto helloAdmin(@QueryParam("visit") boolean visit) {
        return greet(visit);
    }

    @GET
    @Path("/guest")
    @PermitAll
    public HelloDto helloGuest(@QueryParam("visit") boolean visit) {
        return greet(visit);
    }

    private HelloDto greet(boolean visit) {
        String greet = user.toString();
        if (visit) return new HelloDto(greet, Instant.now(), visits++);
        return new HelloDto(greet, Instant.now());
    }
}
