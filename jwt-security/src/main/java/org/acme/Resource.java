package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("")
public class Resource {

    @Inject
    TokenService tokenService;

    @GET
    @Path("/token")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAdminToken(){
        return tokenService.generateToken();
    }

    @GET
    @Path("/user/token")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUsertoken(){
        return tokenService.generateToken();
    }

}
