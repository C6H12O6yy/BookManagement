package com.example.bookmanagement.repositories;

import org.springframework.stereotype.Repository;

import com.example.bookmanagement.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.bookmanagement.entities.ERole;



/**
 * Repository interface for accessing {@link Role} entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a {@link Role} entity by its role name.
     *
     * @param rolename the role name (of type {@link ERole})
     * @return an {@link Optional} containing the found role or empty if no role is found with the given name
     */
    Optional<Role> findByRolename(ERole rolename);
}