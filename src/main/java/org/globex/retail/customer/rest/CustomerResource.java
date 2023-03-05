package org.globex.retail.customer.rest;

import org.globex.retail.customer.model.dto.CustomerDto;
import org.globex.retail.customer.service.CustomerService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/public")
public class CustomerResource {

    @Inject
    CustomerService customerService;

    @GET
    @Path("/customer/id/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerByUserId(@PathParam("userId") String userId) {
        CustomerDto customer = customerService.getCustomerByCustomerId(userId);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(customer).build();
        }
    }

}
