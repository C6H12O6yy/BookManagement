package com.example.bookmanagement.dto.request;


import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.bookmanagement.utils.MessagesConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO for user registration or login requests.
 * Contains the user's username and password with validation constraints.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    /**
     * The username of the user. Must be at least 4 characters long.
     */
    @Size(min = 4,max = 20, message = MessagesConstants.USER_NAME_SIZE)
    @NotBlank(message = MessagesConstants.USER_NAME_NOT_NULL)
    private String username;

    /**
     * The password of the user. Must be at least 6 characters long.
     */
    @Size(min = 6, message = MessagesConstants.PASSWORD_SIZE)
    @NotBlank(message = MessagesConstants.PASSWORD_NOT_NULL)
    private String password;
}