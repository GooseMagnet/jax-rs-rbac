package com.goosemagnet.jaxrs.model;

public enum UserRole {
    ADMIN,
    USER,
    GUEST;

    public static UserRole fromString(String role) {
        try {
            return valueOf(role.toUpperCase());
        } catch (Exception e) {
            return GUEST;
        }
    }
}
