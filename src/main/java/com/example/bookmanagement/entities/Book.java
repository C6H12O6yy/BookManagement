package com.example.bookmanagement.entities;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.bookmanagement.utils.Constants;
import com.example.bookmanagement.utils.MessagesConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Entity class representing a book.
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    @NotBlank(message = MessagesConstants.BOOK_VARIABLES_TITLE + MessagesConstants.BOOK_SIZE_VALIDATION_ERROR)
    @Size(max = 255, message = MessagesConstants.BOOK_VARIABLES_TITLE + MessagesConstants.BOOK_SIZE_VALIDATION_ERROR)
    private String title;

    @Column(name = "published_date")
    @NotNull(message = MessagesConstants.BOOK_VARIABLES_PUBLISHEDDATE + MessagesConstants.BOOK_SIZE_VALIDATION_ERROR)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    private Date publishedDate;

    @Column(name = "genre")
    @NotBlank(message = MessagesConstants.BOOK_VARIABLES_GENRE + MessagesConstants.BOOK_SIZE_VALIDATION_ERROR)
    @Size(max = 255, message = MessagesConstants.BOOK_VARIABLES_GENRE + MessagesConstants.BOOK_SIZE_VALIDATION_ERROR)
    private String genre;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    /**
     * Get the ID of the book.
     *
     * @return the ID of the book
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the title of the book.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
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
     * Get the author of the book.
     *
     * @return the author of the book
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Get the published date of the book.
     *
     * @return the published date of the book
     */
    public Date getPublishedDate() {
        return publishedDate;
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
     * Get the genre of the book.
     *
     * @return the genre of the book
     */
    public String getGenre() {
        return genre;
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
     * Get the description of the book.
     *
     * @return the description of the book
     */
    public String getDescription() {
        return description;
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
