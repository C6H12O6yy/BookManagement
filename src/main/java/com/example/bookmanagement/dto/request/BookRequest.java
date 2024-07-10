package com.example.bookmanagement.dto.request;

import com.example.bookmanagement.entities.Author;
import com.example.bookmanagement.utils.Constants;
import com.example.bookmanagement.utils.MessagesConstants;
import com.example.bookmanagement.utils.TableConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private Long id;
    private String title;
    private Date publishedDate;
    private String genre;
    private String description;
    private Author author;

    /**
     * Set the title of the book.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set the author of the book.
     *
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Set the published date of the book.
     *
     * @param publishedDate the published date to set
     */
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * Set the genre of the book.
     *
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Set the description of the book.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}




