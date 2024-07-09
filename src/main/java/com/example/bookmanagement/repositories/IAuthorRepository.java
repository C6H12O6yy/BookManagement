package com.example.bookmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmanagement.entities.Author;

/**
 * Repository interface for managing Author entities.
 */
public interface IAuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Find authors by their name containing the specified keyword.
     *
     * @param keyword the keyword to search for in author names
     * @return a list of authors whose names contain the keyword
     */
    List<Author> findByAuthorNameContaining(String keyword);
}
