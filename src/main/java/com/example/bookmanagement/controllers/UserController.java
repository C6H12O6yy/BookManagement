package com.example.bookmanagement.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmanagement.dto.request.UserRequest;
import com.example.bookmanagement.entities.User;
import com.example.bookmanagement.services.IUserService;
import com.example.bookmanagement.utils.Constants;
/**
 * Controller for user authentication and registration.
 * Provides endpoints for user signup, signin, and retrieving the current user's information.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * Registers a new user based on the provided request.
     *
     * @param userRequest the details of the user to register
     * @return a ResponseEntity with a success message and HTTP status code
     * @throws ParseException if there is an error parsing any date fields
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserRequest userRequest) throws ParseException {
        userService.signUp(userRequest);
        return new ResponseEntity<>(Constants.SIGNUP_SUCCESS, HttpStatus.CREATED);
    }

    /**
     * Authenticates a user based on the provided request.
     *
     * @param userRequest the credentials of the user to sign in
     * @return a ResponseEntity with the authentication token and HTTP status code
     */
    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userService.login(userRequest));
    }

    /**
     * Retrieves the current logged-in user's information.
     *
     * @return a ResponseEntity containing the current user's details
     */
    @GetMapping("/me")
    public ResponseEntity<User> me() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}