package de.thoughtgang.cloud;

import org.jboss.logging.Logger;

import io.vertx.core.http.HttpServerRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.stream.Collectors;


@Path("/inlet")
public class WebServiceInletResource {

    private static final Logger LOG = Logger.getLogger(WebServiceInletResource.class);

    @POST
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.TEXT_PLAIN)
    public String submit(@Context HttpServerRequest request, InputStream inputStream) {

        Object test = request.headers();
        String contentType = request.getHeader("Content-Type");
        InputStreamReader reader = new InputStreamReader(inputStream);
        String body = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));
        LOG.info(contentType + " ----" + body);

        return "hello";
    }
}
