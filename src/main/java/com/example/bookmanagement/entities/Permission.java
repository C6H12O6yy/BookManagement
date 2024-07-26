package com.example.bookmanagement.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Entity representing a permission within the application.
 * Each permission is identified by its unique name and has a description.
 */
@Entity
public class Permission {
    /**
     * The unique identifier for the permission.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;


    /**
     * The name of the permission.
     */
    private String name;

    /**
     * A endpoint of the permission.
     */
    private String endpoint;

    /**
     * A medthod of the permission.
     */
    private String method;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}