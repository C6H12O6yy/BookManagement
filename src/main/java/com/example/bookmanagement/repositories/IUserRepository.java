package com.example.bookmanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.bookmanagement.entities.User;


/**
 * Repository interface for accessing {@link User} entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a {@link User} entity by its username.
     *
     * @param username the username of the user
     * @return an {@link Optional} containing the found user or empty if no user is found with the given username
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user exists with the given username.
     *
     * @param username the username to check
     * @return true if a user exists with the given username, false otherwise
     */
    boolean existsByUsername(String username);
}