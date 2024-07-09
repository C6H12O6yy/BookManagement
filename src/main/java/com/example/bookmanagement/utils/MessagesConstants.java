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
     * Key for the error message indicating that the book size validation has failed.
     */
    public static final String BOOK_SIZE_VALIDATION_ERROR = "book.size.validation";
    
    /**
     * Key for the error message indicating that a mandatory book field is missing.
     */
    public static final String BOOK_MANDATORY_VALIDATION_ERROR = "book.mandatory.validation";
    
//   
    
    /**
     * Key for the book title variable.
     */
    public static final String BOOK_VARIABLES_TITLE = "book.variables.title";
    
    /**
     * Key for the book published date variable.
     */
    public static final String BOOK_VARIABLES_PUBLISHEDDATE = "book.variables.publishedDate";
    
    /**
     * Key for the book genre variable.
     */
    public static final String BOOK_VARIABLES_GENRE = "book.variables.genre";
    
    /**
     * Key for the book description variable.
     */
    public static final String BOOK_VARIABLES_DESCRIPTION = "book.variables.description";
    
    /**
     * Key for the book author ID variable.
     */
    public static final String BOOK_VARIABLES_AUTHORID = "book.variables.authorId";

}
