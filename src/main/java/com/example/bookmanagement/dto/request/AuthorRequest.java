package com.example.bookmanagement.dto.request;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.example.bookmanagement.configs.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data; 
public class AuthorRequest {
    private Long id;

    @NotBlank(message = "Author name is mandatory")
    @Size(max = 255, message = "Author name must be less than 255 characters")
    private String authorName;

    @NotNull(message = "Birth date is mandatory")
    @PastOrPresent(message = "Birth date must be in the past or present")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    private Date birthDate;

    @NotBlank(message = "Nationality is mandatory")
    @Size(max = 100, message = "Nationality must be less than 100 characters")
    private String nationality;

    @Size(max = 1000, message = "Description must be less than 1000 characters")
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
