package com.goosemagnet.jaxrs.model;

import lombok.*;

import java.time.Instant;
import java.util.Set;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class User {
    String name;
    String email;
    Set<UserRole> roles;
    Instant lastRequest;

    public User(String name, String email, Set<UserRole> roles) {
        this(name, email, roles, Instant.now());
    }
}
