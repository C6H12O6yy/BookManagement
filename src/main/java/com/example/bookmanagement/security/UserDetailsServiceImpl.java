package com.example.bookmanagement.security;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bookmanagement.configs.Translator;
import com.example.bookmanagement.entities.Permission;
import com.example.bookmanagement.entities.Role;
import com.example.bookmanagement.entities.User;
import com.example.bookmanagement.repositories.IUserRepository;
import com.example.bookmanagement.utils.MessagesConstants;


/**
 * Implementation of {@link UserDetailsService} for loading user-specific data.
 * Retrieves user information from the {@link IUserRepository} and converts it into a {@link CustomUserDetails} instance.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    /**
     * Loads user details by username.
     * Retrieves the user from the repository and constructs a {@link CustomUserDetails} object,
     * including roles and permissions as granted authorities.
     *
     * @param username the username of the user to load
     * @return a {@link UserDetails} instance with user information and authorities
     * @throws IllegalArgumentException if the username is null
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException(Translator.toLocale(MessagesConstants.USER_NAME_NOT_NULL));
        }
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(Translator.toLocale(MessagesConstants.USER_NOT_FOUND)));
        
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (Role role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRolename().toString()));
                for (Permission permission: role.getPermissions()) {
                    authorities.add(new SimpleGrantedAuthority(permission.getName()));
                }
            }
            return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), authorities);
        
    }
}