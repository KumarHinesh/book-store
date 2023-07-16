package org.acme;

import com.oracle.svm.core.annotate.Inject;
import io.quarkus.logging.Log;
import io.quarkus.security.Authenticated;
import io.smallrye.jwt.auth.principal.JWTAuthContextInfo;
import io.smallrye.jwt.auth.principal.JWTCallerPrincipal;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dtos.UserDTO;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import java.security.Principal;
import java.util.Arrays;


//@SecurityScheme(scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT")
@Path("")
public class Resource {


    public Response getStudentList(){
        return Response.ok().entity(Arrays.asList("test", "test", "Hello")).build();
    }
    @GET
    @RolesAllowed({"admin", "user"})
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(@Claim UserDTO userDTO){
        Log.debug("8b6876b8y8" +  userDTO.getUsername());
        Log.debug("8b68hsiw73" +  userDTO.getPassword());
        return getStudentList();
    }


    @GET
    @RolesAllowed({"admin", "user"})
    @Path("/get/bools")
  //  @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook2s(){
        return getStudentList();
    }

}
