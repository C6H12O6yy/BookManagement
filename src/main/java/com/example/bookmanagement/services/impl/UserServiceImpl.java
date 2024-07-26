package com.example.bookmanagement.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import java.util.Set;

import javax.naming.AuthenticationException;
import javax.security.auth.message.AuthException;
import javax.validation.Valid;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import com.example.bookmanagement.configs.Translator;
import com.example.bookmanagement.dto.request.UserRequest;

import com.example.bookmanagement.entities.ERole;
import com.example.bookmanagement.entities.Role;
import com.example.bookmanagement.entities.User;
import com.example.bookmanagement.exception.UsernameAlreadyExistsException;
import com.example.bookmanagement.jwt.JwtTokenProvider;
import com.example.bookmanagement.repositories.IRoleRepository;
import com.example.bookmanagement.repositories.IUserRepository;
import com.example.bookmanagement.security.CustomUserDetails;
import com.example.bookmanagement.services.IUserService;
import com.example.bookmanagement.utils.Constants;
import com.example.bookmanagement.utils.MessagesConstants;
/**
 * Service implementation for managing user accounts.
 * Provides functionality to register new users, authenticate users, and retrieve the currently authenticated user.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * Registers a new user with the provided details.
     *
     * @param userRequest the details of the user to register
     * @throws ParseException if the date format is incorrect
     * @throws UsernameAlreadyExistsException if the username already exists
     */
    @Override
    public void signUp( UserRequest userRequest) throws ParseException {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new UsernameAlreadyExistsException(Translator.toLocale(MessagesConstants.USER_NAME_ALREADY_EXIST));
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date dateNow = new Date();
        String strNow = sdf.format(dateNow);
        user.setCreated(sdf.parse(strNow));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRolename(ERole.STAFF).orElseThrow(() -> new IllegalArgumentException(MessagesConstants.ROLE_NOT_NULL)));
        user.setRoles(roles);
        userRepository.save(user);
    }

    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param userRequest the credentials of the user to authenticate
     * @return the generated JWT token
     */
    @Override
    public String login( UserRequest userRequest) throws BadCredentialsException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return tokenProvider.generateToken(customUserDetails);
    }

    /**
     * Retrieves the ID of the currently authenticated user.
     *
     * @return the ID of the current user
     */
    public Long getCurrentUserId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getId();
    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return the current user
     * @throws ResponseStatusException if the user is not found
     */
    @Override
    public User getCurrentUser() {
        return userRepository.findById(getCurrentUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Translator.toLocale(MessagesConstants.USER_NOT_FOUND)));
    }
}
