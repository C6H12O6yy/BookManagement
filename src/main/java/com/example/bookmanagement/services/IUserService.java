package com.example.bookmanagement.services;

import java.text.ParseException;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.BadCredentialsException;

import com.example.bookmanagement.dto.request.UserRequest;
import com.example.bookmanagement.entities.User;

/**
 * Service interface for managing user-related operations.
 * Provides methods for user registration, login, and retrieving current user details.
 */
public interface IUserService {

    /**
     * Registers a new user with the provided details.
     *
     * @param userRequest the details of the user to register
     * @throws ParseException if there is an error parsing the date
     */
    void signUp(final UserRequest userRequest) throws ParseException;

    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param userRequest the login credentials of the user
     * @return the JWT token for the authenticated user
     * @throws BadCredentialsException 
     */
    String login(final UserRequest userRequest) throws BadCredentialsException;

    /**
     * Retrieves the currently authenticated user.
     *
     * @return the details of the currently authenticated user
     */
    User getCurrentUser();
}