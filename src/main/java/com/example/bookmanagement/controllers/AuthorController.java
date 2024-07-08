package com.example.bookmanagement.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bookmanagement.configs.Constants;
import com.example.bookmanagement.dto.request.AuthorRequest;
import com.example.bookmanagement.dto.response.AuthorResponse;
import com.example.bookmanagement.services.AuthorService;

import org.springframework.data.domain.Pageable;

/**
 * REST controller for managing authors.
 */
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /**
     * Get all authors with pagination.
     *
     * @param page the page number, default is {@value Constants#DEFAULT_PAGE_NUMBER}
     * @param size the page size, default is {@value Constants#DEFAULT_PAGE_SIZE}
     * @return a {@link ResponseEntity} containing a page of {@link AuthorResponse}
     */
    @GetMapping
    public ResponseEntity<Page<AuthorResponse>> getAllAuthors(@RequestParam(defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                              @RequestParam(defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(authorService.findAll(pageable));
    }

    /**
     * Get an author by ID.
     *
     * @param id the ID of the author
     * @return a {@link ResponseEntity} containing the {@link AuthorResponse} of the requested author
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable final Long id) {
        return ResponseEntity.ok(authorService.get(id));
    }

    /**
     * Create a new author.
     *
     * @param authorRequest the request object containing the details of the new author
     * @return a {@link ResponseEntity} containing the ID of the created author
     */
    @PostMapping
    public ResponseEntity<Long> createAuthor(@Valid @RequestBody final AuthorRequest authorRequest) {
        return new ResponseEntity<>(authorService.create(authorRequest), HttpStatus.CREATED);
    }

    /**
     * Update an existing author.
     *
     * @param id the ID of the author to update
     * @param authorRequest the request object containing the updated details of the author
     * @return a {@link ResponseEntity} with an empty body
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable final Long id, @Valid @RequestBody final AuthorRequest authorRequest) {
        authorService.update(id, authorRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * Delete an author by ID.
     *
     * @param id the ID of the author to delete
     * @return a {@link ResponseEntity} with an empty body
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable final Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Search for authors by keyword.
     *
     * @param keyword the keyword to search for
     * @return a {@link ResponseEntity} containing a list of {@link AuthorResponse} that match the search keyword
     */
    @GetMapping("/search")
    public ResponseEntity<List<AuthorResponse>> searchAuthors(@RequestParam("q") final String keyword) {
        if (keyword.isEmpty()) {
            throw new IllegalArgumentException("Keyword must not be empty");
        }
        List<AuthorResponse> authors = authorService.search(keyword);
        return ResponseEntity.ok(authors);
    }
}
