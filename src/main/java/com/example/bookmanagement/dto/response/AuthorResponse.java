package com.example.bookmanagement.dto.response;

import java.util.Date;

import com.example.bookmanagement.configs.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

public class AuthorResponse {
    private Long id;
    private String authorName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    private Date birthDate;

    private String nationality;

    private String description;

    public Long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
