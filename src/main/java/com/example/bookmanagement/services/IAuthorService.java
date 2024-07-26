package com.example.bookmanagement.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.bookmanagement.dto.request.AuthorRequest;
import com.example.bookmanagement.dto.response.AuthorResponse;

public interface IAuthorService {

    /**
     * Retrieve all authors with pagination.
     *
     * @param pageable pagination information
     * @return a page of author responses
     */
    public Page<AuthorResponse> findAll(Pageable pageable);

    /**
     * Retrieve an author by ID.
     *
     * @param id the ID of the author to retrieve
     * @return the author response
     */
    public AuthorResponse get(final Long id);

    /**
     * Create a new author.
     *
     * @param authorRequest the request object containing author details
     * @return the ID of the created author
     */
    public Long create(final AuthorRequest authorRequest);

    /**
     * Update an existing author.
     *
     * @param id the ID of the author to update
     * @param authorRequest the request object containing updated author details
     * @return author responses matching the search criteria
     */
    public AuthorResponse update(final Long id, final AuthorRequest authorRequest);

    /**
     * Delete an author by ID.
     *
     * @param id the ID of the author to delete
     */
    public void delete(final Long id);

    /**
     * Search for authors by name.
     *
     * @param authorName the name of the author to search for
     * @return a list of author responses matching the search criteria
     */
    public List<AuthorResponse> search(final String authorName);
}
