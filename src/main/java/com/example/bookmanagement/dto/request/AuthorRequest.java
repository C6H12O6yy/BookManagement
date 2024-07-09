package com.example.bookmanagement.dto.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.example.bookmanagement.utils.Constants;
import com.example.bookmanagement.utils.MessagesConstants;
import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO class representing the request payload for creating or updating an author.
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthorRequest {
    private Long id;

   
    @NotNull(message = MessagesConstants.AUTHOR_NAME_MANDATORY)
    @Size(max = 255, message =MessagesConstants.AUTHOR_NAME_SIZE )
    private String authorName;

    @NotNull(message =  MessagesConstants.BIRTH_DATE_MANDATORY )
    @PastOrPresent(message = MessagesConstants.BIRTH_DATE_PAST_OR_PRESENT )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    private Date birthDate;

    @NotBlank(message = MessagesConstants.NATIONALITY_MANDATORY )
    @Size(max = 100, message = MessagesConstants.NATIONALITY_SIZE)
    private String nationality;

    @Size(max = 1000, message = MessagesConstants.DESCRIPTION_SIZE )
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
