package com.example.bookmanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmanagement.entities.Book;

/**
 * Repository interface for managing Book entities.
 */
public interface IBookRepository extends JpaRepository<Book, Long> {

    /**
     * Find a book by its title.
     *
     * @param title the title of the book to search for
     * @return an Optional containing the book with the specified title, or empty if not found
     */
    public Optional<Book> findByTitle(String title);
}
