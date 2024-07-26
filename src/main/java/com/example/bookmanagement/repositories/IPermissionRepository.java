package com.example.bookmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmanagement.entities.Permission;
import java.util.List;
import java.util.Optional;


/**
 * Repository interface for accessing {@link Permission} entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {

    /**
     * Finds a {@link Permission} entity by its name.
     *
     * @param name the name of the permission
     * @return an {@link Optional} containing the found permission or empty if no permission is found with the given name
     */
    Optional<Permission> findByName(String name);
}