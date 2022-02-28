package org.example.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public abstract class AbstractRoute extends RouteBuilder {

    @Autowired
    private Environment env;

    public RouteDefinition fromQuartzCron(String name, String propertyName) {
        return from("quartz://" + name + "?cron=" + env.getProperty(propertyName));
    }
}
