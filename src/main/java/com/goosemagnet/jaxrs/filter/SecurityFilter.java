package com.goosemagnet.jaxrs.filter;

import com.goosemagnet.jaxrs.model.User;
import com.goosemagnet.jaxrs.model.UserRole;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Provider;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class SecurityFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Context
    private Provider<User> userProvider;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        log.info("User {} accessing method: {}", userProvider.get(), resourceInfo.getResourceMethod());

        Method method = resourceInfo.getResourceMethod();

        if (!permitAll(method) && rolesAllowed(method)) {
            RolesAllowed annotation = method.getAnnotation(RolesAllowed.class);
            Set<String> rolesAllowed = Set.of(annotation.value());

            boolean userHasAnyRequiredRole = userProvider.get()
                    .getRoles()
                    .stream()
                    .map(UserRole::toString)
                    .anyMatch(rolesAllowed::contains);

            if (!userHasAnyRequiredRole) {
                throw new ForbiddenException();
            }
        }
    }

    private boolean permitAll(Method method) {
        return Objects.nonNull(method.getAnnotation(PermitAll.class));
    }

    private boolean rolesAllowed(Method method) {
        return Objects.nonNull(method.getAnnotation(RolesAllowed.class));
    }
}
