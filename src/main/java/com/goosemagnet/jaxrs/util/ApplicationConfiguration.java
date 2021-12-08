package com.goosemagnet.jaxrs.util;

import com.goosemagnet.jaxrs.filter.SecurityFilter;
import com.goosemagnet.jaxrs.filter.UserContext;
import com.goosemagnet.jaxrs.model.User;
import com.goosemagnet.jaxrs.resource.HelloResource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfiguration extends ResourceConfig {

    public ApplicationConfiguration() {
        register(HelloResource.class);
        register(JacksonMapper.class);
        register(SecurityFilter.class);
        registerUserContext();
    }

    private void registerUserContext() {
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(UserContext.class)
                        .to(User.class)
                        .in(RequestScoped.class);
            }
        });
    }
}
