package de.thoughtgang.cloud;

import de.thoughtgang.cloud.customer.Customer;
import io.vertx.core.http.HttpServerRequest;
import org.jboss.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customer")
public class WebServiceCustomerResource {
    private static final Logger LOG = Logger.getLogger(WebServiceCustomerResource.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String submit(Customer customer) {

        customer.toString();

        LOG.info(customer + " ------ CUSTOMER xxxx-----> ");

        return "Hello Customer!";
    }
}
