package com.example.bookmanagement.security;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.bookmanagement.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Custom implementation of {@link UserDetails} for Spring Security.
 * Holds user-specific data and authorities.
 */
@Data
public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 19644740760043255L;

    private Long id;
    private String username;

    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructs a {@link CustomUserDetails} instance with specified parameters.
     *
     * @param id the user's ID
     * @param username the user's username
     * @param password the user's password
     * @param authorities the user's authorities
     */
    public CustomUserDetails(Long id, String username, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
