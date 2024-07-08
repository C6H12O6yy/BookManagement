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

@RestController
@RequestMapping("/authors")

public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<Page<AuthorResponse>> getAllAuthors(@RequestParam(defaultValue =  Constants.DEFAULT_PAGE_NUMBER) int page,
                                              @RequestParam(defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(authorService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable final Long id) {
        return ResponseEntity.ok(authorService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createAuthor(@Valid @RequestBody final AuthorRequest authorRequest) {
        return new ResponseEntity<>(authorService.create(authorRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable final Long id,@Valid @RequestBody final AuthorRequest authorRequest) {
        authorService.update(id, authorRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable final Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<AuthorResponse>> searchAuthors(@RequestParam("q") final String keyword) {
        if (keyword.isEmpty()) {
            throw new IllegalArgumentException("Keyword must not be empty");
        }
        List<AuthorResponse> authors = authorService.search(keyword);
        return ResponseEntity.ok(authors);
    }
}
