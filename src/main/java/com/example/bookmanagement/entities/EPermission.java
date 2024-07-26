package com.example.bookmanagement.entities;

import java.util.Set;

/**
 * Enumeration representing various permissions in the application.
 * 
 * Each permission is associated with a specific HTTP endpoint and method.
 * 
 */
public enum EPermission {
    PERMISSION_UPDATE_AUTHOR("/authors/{id}", "PUT"),
    PERMISSION_GET_ALL_AUTHOR("/authors", "GET"),
    PERMISSION_CREATE_AUTHOR("/authors", "POST"),
    PERMISSION_GET_AUTHOR("/authors/{id}", "GET"),
    PERMISSION_DELETE_AUTHOR("/authors/{id}", "DELETE"),
    PERMISSION_UPDATE_BOOK("/books/{id}", "PUT"),
    PERMISSION_GET_ALL_BOOK("/books", "GET"),
    PERMISSION_CREATE_BOOK("/books", "POST"),
    PERMISSION_GET_BOOK("/books/{id}", "GET"),
    PERMISSION_DELETE_BOOK("/books/{id}", "DELETE");

    private final String endpoint;
    private final String method;

    /**
     * Constructs an {@link EPermission} with the specified endpoint and method.
     *
     * @param endpoint the URL endpoint associated with the permission.
     * @param method the HTTP method associated with the permission.
     */
    EPermission(String endpoint, String method) {
        this.endpoint = endpoint;
        this.method = method;
    }

    /**
     * Returns the URL endpoint associated with the permission.
     *
     * @return the URL endpoint.
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Returns the HTTP method associated with the permission.
     *
     * @return the HTTP method.
     */
    public String getMethod() {
        return method;
    }

   
}
