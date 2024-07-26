package com.example.bookmanagement.exception;

/**
 * Exception thrown when a username that is being registered already exists in the system.
 * Extends {@link RuntimeException} to indicate an application-level error.
 */
public class UsernameAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new {@link UsernameAlreadyExistsException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}