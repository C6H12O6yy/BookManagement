package com.example.bookmanagement.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * Custom implementation of {@link AuthenticationEntryPoint} for handling authentication errors.
 * Delegates the error handling to a {@link HandlerExceptionResolver} to resolve exceptions.
 */
@Component("delegatedAuthenticationEntryPoint")
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * The {@link HandlerExceptionResolver} used to handle exceptions.
     */
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    /**
     * Handles authentication exceptions by delegating to the {@link HandlerExceptionResolver}.
     *
     * @param request the HTTP request during which the authentication exception occurred
     * @param response the HTTP response to send
     * @param authException the authentication exception that was thrown
     * @throws IOException if an input or output error occurs
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) 
      throws IOException, ServletException {
        resolver.resolveException(request, response, null, authException);
    }
}