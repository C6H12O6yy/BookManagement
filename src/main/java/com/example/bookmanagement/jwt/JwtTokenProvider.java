package com.example.bookmanagement.jwt;

import java.lang.reflect.MalformedParametersException;
import java.util.Date;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.bookmanagement.configs.Translator;
import com.example.bookmanagement.security.CustomUserDetails;
import com.example.bookmanagement.utils.MessagesConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;


/**
 * Component responsible for generating, extracting, and validating JWT tokens.
 * Uses the JWT secret and expiration time configured in application properties.
 */
@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.expiration}")
    private int JWT_EXPIRATION;

    /**
     * Generates a JWT token for the provided user details.
     * Ensures that the user details and username are not null or empty.
     *
     * @param customUserDetails the user details for generating the token
     * @return the generated JWT token
     * @throws IllegalArgumentException if customUserDetails or username is null or empty
     */
    public String generateToken(CustomUserDetails customUserDetails) {
        if (customUserDetails == null) {
            throw new IllegalArgumentException(MessagesConstants.USER_NOT_NULL);
        }

        String username = customUserDetails.getUsername();
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException(MessagesConstants.USER_NAME_NOT_NULL);
        }

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    /**
     * Extracts the username from a given JWT token.
     *
     * @param token the JWT token
     * @return the username contained in the token
     * @throws IllegalArgumentException if the token is null
     */
    public String getUserNameFromJwt(String token) {
        if (token == null) {
            throw new IllegalArgumentException(Translator.toLocale(MessagesConstants.TOKEN_NOT_NULL));
        }
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    /**
     * Validates the given JWT token.
     *
     * @param token the JWT token to be validated
     * @return true if the token is valid, false otherwise
     * @throws IllegalArgumentException if the token is null
     */
    public boolean validateToken(String token) {
        if (token == null) {
            throw new IllegalArgumentException(Translator.toLocale(MessagesConstants.TOKEN_NOT_NULL));
        }
        try {
            Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT Token");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.error("JWT Claims String is empty");
        }
        return false;
    }
}