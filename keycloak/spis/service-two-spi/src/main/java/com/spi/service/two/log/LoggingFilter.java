package com.spi.service.two.log;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingFilter implements ClientRequestFilter {
    private static final Logger LOG = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        LOG.log(Level.INFO, "URL: " + requestContext.getUri().toString());
    }
}
