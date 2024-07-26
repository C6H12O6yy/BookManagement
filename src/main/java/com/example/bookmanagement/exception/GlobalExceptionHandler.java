package com.example.bookmanagement.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.bookmanagement.configs.Translator;
import com.example.bookmanagement.utils.Constants;
import com.example.bookmanagement.utils.MessagesConstants;

import lombok.extern.slf4j.Slf4j;
/**
 * Global exception handler for handling various exceptions across the application.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Exception handler for DataAccessException.
     *
     * @param exception the exception that occurred
     * @param webRequest the current web request
     * @return ResponseEntity containing ErrorDetails
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorDetails> handleDataAccessException(DataAccessException exception,
                                                                  WebRequest webRequest) {
        log.error(Constants.LOG_DATA_ACCESS_EXCEPTION, exception.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(),  exception.getMessage(),
        webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    
    /**
     * Exception handler for MethodArgumentNotValidException (Validation errors).
     *
     * @param ex the MethodArgumentNotValidException
     * @param request the current web request
     * @return ResponseEntity containing ErrorDetails
     */
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                     WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
        .map(error -> Translator.toLocale(error.getDefaultMessage()))
        .collect(Collectors.toList());                                                            
        ErrorDetails errorDetails = new ErrorDetails(new Date(),Translator.toLocale(MessagesConstants.VALIDATION_FAILED_MESSAGE), errorMessages.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

        /**
     * Exception handler to handle ResourceNotFoundException.
     *
     * @param exception   The ResourceNotFoundException to handle.
     * @param webRequest  The current web request.
     * @return ResponseEntity containing an ErrorDetails object and HTTP status 404 (Not Found).
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(NotFoundException exception,
                                                                        WebRequest webRequest) {
        log.error(Constants.LOG_NOT_FOUND_EXCEPTION, exception.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link IllegalArgumentException} and returns a response with HTTP status 404 (Not Found).
     *
     * @param exception the {@link IllegalArgumentException} that occurred
     * @param webRequest the current web request
     * @return ResponseEntity containing ErrorDetails with HTTP status 404
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException exception,
                                                                        WebRequest webRequest) {
        log.error(Constants.LOG_RESOURCE_NOT_FOUND_EXCEPTION, exception.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles general {@link Exception} and returns a response with HTTP status 500 (Internal Server Error).
     *
     * @param exception the {@link Exception} that occurred
     * @param webRequest the current web request
     * @return ResponseEntity containing ErrorDetails with HTTP status 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
                                                              WebRequest webRequest) {
        log.error(Constants.LOG_EXCEPTION, exception.getMessage());                                        
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
               webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles {@link UsernameAlreadyExistsException} and returns a response with HTTP status 409 (Conflict).
     *
     * @param exception the {@link UsernameAlreadyExistsException} that occurred
     * @param webRequest the current web request
     * @return ResponseEntity containing ErrorDetails with HTTP status 409
     */
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException exception,
                                                                        WebRequest webRequest) {
        log.error(Constants.LOG_USERNAME_EXCEPTION, exception.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException exception,
                                                                        WebRequest webRequest) {
        log.error(Constants.LOG_BADCREDENTIALS_EXCEPTION, exception.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles {@link AuthenticationException} and returns a response with HTTP status 401 (Unauthorized).
     *
     * @param exception the {@link AuthenticationException} that occurred
     * @param webRequest the current web request
     * @return ResponseEntity containing ErrorDetails with HTTP status 401
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDetails> handleAuthenticationException(AuthenticationException exception,
                                                                      WebRequest webRequest) {
        log.error("Authentication Error: {}", exception.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Authentication failed.",
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
    

    

}
