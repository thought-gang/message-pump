package de.thoughtgang.cloud;

import de.thoughtgang.cloud.base.Message;
import de.thoughtgang.cloud.core.Dispatcher;
import de.thoughtgang.cloud.message.ByteMessage;
import de.thoughtgang.cloud.message.TextMessage;
import de.thoughtgang.cloud.outlet.FileOutlet;
import de.thoughtgang.cloud.util.ConfigurationError;
import org.jboss.logging.Logger;

import io.vertx.core.http.HttpServerRequest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.Map;
import java.util.stream.Collectors;


@Path("/inlet")
public class WebServiceInletResource {

    @Inject
    private Dispatcher dispatcher;

    private static final Logger LOG = Logger.getLogger(WebServiceInletResource.class);

    @POST
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.TEXT_PLAIN)
    public Response submit(@Context HttpServerRequest request, InputStream is) throws IOException {

        Object test = request.headers();
        String contentType = request.getHeader("Content-Type");
        Message message;

        if (contentType != null && (contentType.startsWith("text/") || "application/xhtml+xml".equals(contentType) || "application/xml".equals(contentType))) {
            InputStreamReader reader = new InputStreamReader(is);
            String body = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));
            message = new TextMessage(body);
            LOG.info(contentType + " ----" + body);
        } else {
            byte[] body = is.readAllBytes();
            message = new ByteMessage(body);
        }

        message.getHeaders().put("Content-Type", contentType);
        try {
            dispatcher.dispatch(message);
        } catch (ConfigurationError e) {
            e.printStackTrace();
        }

        Response.ResponseBuilder rb = Response.ok("hello");
        for (Map.Entry<String, String> header : message.getHeaders().entrySet()) {

            rb.header("X-MessagePump-" + header.getKey(), header.getValue());

        }


        return rb.build() ;
    }
}
