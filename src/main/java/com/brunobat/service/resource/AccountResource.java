package com.brunobat.service.resource;

import com.brunobat.model.Owner;
import com.brunobat.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Path("/owner")
@Produces("application/json")
public class AccountResource extends Application implements Serializable{

    private static final Logger LOG = LoggerFactory.getLogger(AccountResource.class);

    @Inject
    private AccountService accountService;

    @GET
    @Path("/{id}")
    public Response getOwnerDetail(@PathParam("id") String ownerId) {
        try {
            Owner owner = accountService.getOwner(ownerId);
            if(owner==null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }else {
                return Response.ok().entity(owner).build();
            }
        } catch (Exception e) {
            LOG.error("Error getting owner with id: {}", ownerId);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}