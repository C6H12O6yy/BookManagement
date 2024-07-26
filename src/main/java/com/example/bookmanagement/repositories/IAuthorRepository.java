package com.example.bookmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import com.example.bookmanagement.entities.Author;
import com.example.bookmanagement.utils.Constants;

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
   public  List<Author> findByAuthorNameContaining(String keyword);

    /**
     * Retrieves a list of authors along with their associated books using a native SQL query.
     *
     * @return a list of authors with their associated books.
     * 
     * @see Constants#GET_AUTHOR_WITH_BOOKS
     */
    @Query(value = Constants.GET_AUTHOR_WITH_BOOKS, nativeQuery = true)
    public List<Author> getAuthorsWithBooks();


}
