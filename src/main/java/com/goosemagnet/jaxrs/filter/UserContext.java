package com.goosemagnet.jaxrs.filter;

import com.goosemagnet.jaxrs.model.User;
import com.goosemagnet.jaxrs.model.UserRole;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.hk2.api.Factory;

import java.util.Set;

@Slf4j
public class UserContext implements Factory<User> {

    @Context
    private UriInfo uri;

    @Override
    public User provide() {
        String role = uri.getQueryParameters().getFirst("role");
        UserRole userRole = UserRole.fromString(role);
        return new User(userRole.name(), userRole.toString().toLowerCase() + "@email.com", Set.of(userRole));
    }

    @Override
    public void dispose(User user) {
        // NO-OP
    }
}
