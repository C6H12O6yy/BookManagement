package com.example.bookmanagement.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.bookmanagement.security.UserDetailsServiceImpl;
import com.example.bookmanagement.utils.Constants;

import lombok.extern.slf4j.Slf4j;
/**
 * Filter for validating JWT tokens and setting the authentication in the security context.
 * Extends {@link OncePerRequestFilter} to ensure the filter is executed once per request.
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsServiceImpl customUserDetailsService;

    /**
     * Extracts the JWT from the request header.
     *
     * @param request the HTTP request containing the JWT
     * @return the JWT token, or null if not found or not valid
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.HEADER_AUTHORIZATION_STRING);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.JWT_BEARER)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Processes the request by validating the JWT and setting the authentication in the security context.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain to pass the request and response through
     * @throws ServletException if an error occurs during the filter processing
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
            String username = jwtTokenProvider.getUserNameFromJwt(jwt);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}