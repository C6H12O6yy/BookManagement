package com.example.bookmanagement.entities;
import javax.persistence.*;

import lombok.*;

import java.util.Set;

/**
 * Entity representing a role within the application.
 * Each role has a unique identifier, a name represented by an enum, and a set of associated permissions.
 */
@Entity
public class Role {

    /**
     * The unique identifier for the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * The name of the role, represented by the ERole enum.
     */
    @Column
    @Enumerated(EnumType.STRING)
    private ERole rolename;

    /**
     * The set of permissions associated with the role.
     */
    @ManyToMany
    private Set<Permission> permissions;
    public Long getId() {
        return id;
    }
    public ERole getRolename() {
        return rolename;
    }
    public void setRolename(ERole rolename) {
        this.rolename = rolename;
    }
    public Set<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}