package com.example.bookmanagement.entities;
import com.example.bookmanagement.utils.Constants;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Entity representing a user within the application.
 * Each user has a unique identifier, a username, a password, and a creation date.
 * The user also has a set of roles associated with them.
 */
@Entity
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user. This field is mandatory.
     */
    @Column(nullable = false)
    private String username;

    /**
     * The password of the user. This field is mandatory and will be ignored during JSON serialization.
     */
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    /**
     * The date when the user was created, formatted according to the specified date pattern.
     */
    @Column(name = "created")
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private Date created;

    /**
     * The set of roles assigned to the user. This relationship is eagerly fetched and uses a join table for mapping.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return id;
    }




    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Date getCreated() {
        return created;
    }


    public void setCreated(Date created) {
        this.created = created;
    }


    public Set<Role> getRoles() {
        return roles;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}