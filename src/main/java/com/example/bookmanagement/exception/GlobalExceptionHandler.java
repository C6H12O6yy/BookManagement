package com.example.bookmanagement.exception;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(DataAccessException.class)
        public ResponseEntity<ErrorDetails> handleDataAccessException(DataAccessException exception,
                                                                                WebRequest webRequest){
                ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                        webRequest.getDescription(false));
                return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
                                                                WebRequest webRequest){
                ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                        webRequest.getDescription(false));
                return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorDetails> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
            List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
            List<String> errorMessages = fieldErrors.stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed", errorMessages.toString());
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
}
}