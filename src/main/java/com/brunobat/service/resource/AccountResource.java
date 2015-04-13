package com.brunobat.service.resource;

import com.brunobat.model.Owner;
import com.brunobat.service.AccountService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@ApplicationPath("/app")
@Produces("application/json")
public class AccountResource extends Application {

    @Inject
    private AccountService accountService;

    @GET
    @Path("owner/{id}/detail")
    public Response getOwnerDetail(@PathParam("id") String ownerId) {
        try {
            Owner owner = accountService.getOwner(ownerId);
            return Response.ok().entity(owner).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}