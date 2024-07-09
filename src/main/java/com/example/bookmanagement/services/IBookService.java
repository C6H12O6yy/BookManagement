package com.example.bookmanagement.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.bookmanagement.entities.Book;

public interface IBookService {

    /**
     * Saves a new book or updates an existing one.
     *
     * @param book the book entity to save or update
     * @return the saved or updated book entity
     */
    Book saveBook(Book book);

    /**
     * Updates an existing book identified by its ID.
     *
     * @param id the ID of the book to update
     * @param bookDetails the details of the book to update
     * @return the updated book entity
     */
    Book updateBook(Long id, Book bookDetails);

    /**
     * Deletes a book by its ID.
     *
     * @param id the ID of the book to delete
     */
    void deleteBook(Long id);

    /**
     * Retrieves all books with pagination support.
     *
     * @param pageable pagination information
     * @return a page of books
     */
    Page<Book> getBooks(Pageable pageable);

    /**
     * Retrieves a book by its title.
     *
     * @param title the title of the book to retrieve
     * @return an Optional containing the book entity if found, otherwise empty
     */
    Optional<Book> getBookByTitle(String title);

}
