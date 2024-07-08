package com.example.bookmanagement.dto.response;

import java.util.Date;

import com.example.bookmanagement.configs.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * DTO class representing the response payload for author details.
 */
@Data
public class AuthorResponse {
    private Long id;
    private String authorName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    private Date birthDate;

    private String nationality;
    private String description;

    /**
     * Get the ID of the author.
     *
     * @return the ID of the author
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the name of the author.
     *
     * @return the name of the author
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Get the birth date of the author.
     *
     * @return the birth date of the author
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Get the nationality of the author.
     *
     * @return the nationality of the author
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Get the description of the author.
     *
     * @return the description of the author
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the ID of the author.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Set the name of the author.
     *
     * @param authorName the name to set
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * Set the birth date of the author.
     *
     * @param birthDate the birth date to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Set the nationality of the author.
     *
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Set the description of the author.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
