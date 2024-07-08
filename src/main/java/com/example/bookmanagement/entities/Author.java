package com.example.bookmanagement.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.bookmanagement.configs.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity class representing an author.
 */
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "author_name", nullable = false)
    private String authorName;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    private Date birthDate;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    /**
     * Get the ID of the author.
     *
     * @return the ID of the author
     */
    public Long getId() {
        return id;
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
     * Get the name of the author.
     *
     * @return the name of the author
     */
    public String getAuthorName() {
        return authorName;
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
     * Get the birth date of the author.
     *
     * @return the birth date of the author
     */
    public Date getBirthDate() {
        return birthDate;
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
     * Get the nationality of the author.
     *
     * @return the nationality of the author
     */
    public String getNationality() {
        return nationality;
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
     * Get the description of the author.
     *
     * @return the description of the author
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the author.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the list of books associated with the author.
     *
     * @return the list of books associated with the author
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Set the list of books associated with the author.
     *
     * @param books the list of books to set
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
