package com.example.bookmanagement.utils;

import org.springframework.stereotype.Component;

/**
 * A class that holds constant keys for validation and error messages used throughout the application.
 */
@Component
public class MessagesConstants {

    /**
     * Key for the error message indicating that the author name is mandatory.
     */
    public static final String AUTHOR_NAME_MANDATORY = "author.name.mandatory";

    /**
     * Key for the error message indicating that the author name must be less than a specified number of characters.
     */
    public static final String AUTHOR_NAME_SIZE = "author.name.size";

    /**
     * Key for the error message indicating that the birth date is mandatory.
     */
    public static final String BIRTH_DATE_MANDATORY = "author.birthDate.mandatory";

    /**
     * Key for the error message indicating that the birth date must be in the past or present.
     */
    public static final String BIRTH_DATE_PAST_OR_PRESENT = "author.birthDate.pastOrPresent";

    /**
     * Key for the error message indicating that the nationality is mandatory.
     */
    public static final String NATIONALITY_MANDATORY = "author.nationality.mandatory";

    /**
     * Key for the error message indicating that the nationality must be less than a specified number of characters.
     */
    public static final String NATIONALITY_SIZE = "author.nationality.size";

    /**
     * Key for the error message indicating that the description must be less than a specified number of characters.
     */
    public static final String DESCRIPTION_SIZE = "author.description.size";

    /**
     * Key for the error message indicating that validation has failed.
     */
    public static final String VALIDATION_FAILED_MESSAGE = "validation.failed";

    /**
     * Key for the error message indicating that an author with the specified ID was not found.
     */
    public static final String AUTHOR_NOT_FOUND_ERROR = "author.not-found";
    
    /**
     * Constant representing the success message template for author deletion.
     * Use with String.format to provide the specific author ID.
     */
    public static final String AUTHOR_DELETE_SUCCESS = "author.delete.success";

    /**
     * Constant representing the success message template for author update.
     * Use with String.format to provide the specific author ID.
     */
    public static final String AUTHOR_UPDATE_SUCCESS = "author.update.success";



    /**
     * Key for the error message indicating that an book with the specified ID was not found.
     */
    public static final String BOOK_NOT_FOUND_ERROR = "book.not-found";

    /**
     * Key for the error message indicating that title must be less than a specified number of characters.
     */
    public static final String BOOK_TITLE_SIZE = "book.title.size";

    /**
     * Key for the error message indicating that title is mandatory.
     */
    public static final String BOOK_TITLE_MANDATORY = "book.title.mandatory";

    /**
     * Key for the error message indicating that published date is mandatory.
     */
    public static final String BOOK_PUBLISHEDDATE_MANDATORY = "book.publishedDate.mandatory";

    /**
     * Key for the error message indicating that genre must be less than a specified number of characters.
     */
    public static final String BOOK_GENRE_SIZE = "book.genre.size";

    /**
     * Key for the error message indicating that genre is mandatory.
     */
   // Validation and Error Messages for Book
   public static final String BOOK_GENRE_MANDATORY = "book.genre.mandatory";

   // Error Messages for User
   public static final String USER_NOT_FOUND = "user.not-found";
   public static final String PASSWORD_NOT_NULL = "user.password.not-null";
   public static final String USER_NAME_NOT_NULL = "user.username.not-null";
   public static final String USER_NAME_SIZE = "user.username.size";
   public static final String PASSWORD_SIZE = "user.password.size";
   public static final String USER_NOT_NULL = "user.not-null";
   public static final String USER_NAME_ALREADY_EXIST = "user.username.already-exist";

   // Error Messages for JWT Token
   public static final String TOKEN_NOT_NULL = "jwt.token.not-null";

   // Error Messages for Permission
   public static final String PERMISSION_NOT_NULL = "permission.not-null";
   public static final String PERMISSION_NOT_FOUND = "permission.not-found";

   // Error Messages for Role
   public static final String ROLE_NOT_NULL = "role.not-null";
   public static final String ROLE_NOT_FOUND = "role.not-found";

}
