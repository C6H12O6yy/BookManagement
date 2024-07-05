package com.example.bookmanagement.dto.response;

import java.util.Date;

import com.example.bookmanagement.configs.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AuthorResponse {
    private Long id;
    private String authorName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    private Date birthDate;

    private String nationality;

    private String description;
    
}
