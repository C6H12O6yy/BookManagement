package com.example.bookmanagement.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Custom implementation of {@link AccessDeniedHandler} to handle {@link AccessDeniedException}.
 * 
 * This class responds to access denial with a JSON error message and a {@code 403 Forbidden} status.
 * The response includes details such as the timestamp, error message, and the requested URI.
 * 
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ErrorDetails errorDetails = new ErrorDetails(
            new Date(),
            "You do not have permission to access this resource.",
            request.getRequestURI()
        );
        out.print(new ObjectMapper().writeValueAsString(errorDetails));
        out.flush();
    }
}
