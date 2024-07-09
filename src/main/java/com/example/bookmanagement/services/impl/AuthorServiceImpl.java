package com.example.bookmanagement.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookmanagement.configs.Translator;
import com.example.bookmanagement.dto.request.AuthorRequest;
import com.example.bookmanagement.dto.response.AuthorResponse;
import com.example.bookmanagement.entities.Author;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.repositories.IAuthorRepository;
import com.example.bookmanagement.services.IAuthorService;
import com.example.bookmanagement.utils.MessagesConstants;

@Service
public class AuthorServiceImpl implements IAuthorService {

    private final IAuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(IAuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Search authors by keyword in their names.
     *
     * @param keyword the keyword to search for in author names
     * @return a list of AuthorResponse objects matching the search criteria
     */
    @Override
    public List<AuthorResponse> search(String keyword) {
        List<Author> authors = authorRepository.findByAuthorNameContaining(keyword);
        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieve all authors with pagination.
     *
     * @param pageable pagination information
     * @return a page of AuthorResponse objects
     */
    @Override
    public Page<AuthorResponse> findAll(Pageable pageable) {
        Page<Author> authors = authorRepository.findAll(pageable);
        return authors.map(author -> modelMapper.map(author, AuthorResponse.class));
    }

    /**
     * Retrieve an author by ID.
     *
     * @param id the ID of the author to retrieve
     * @return the AuthorResponse object corresponding to the ID
     * @throws IllegalArgumentException if no author is found with the given ID
     */
    @Override
    public AuthorResponse get(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        System.out.println(author.get().getAuthorName());
        if (author.isPresent()) {
            return modelMapper.map(author.get(), AuthorResponse.class);
        } else {
            throw new ResourceNotFoundException(Translator.toLocale(MessagesConstants.AUTHOR_NOT_FOUND_ERROR)+id);
        }
    }

    /**
     * Create a new author.
     *
     * @param authorRequest the AuthorRequest object containing author information
     * @return the ID of the created author
     */
    @Override
    @Transactional
    public Long create(AuthorRequest authorRequest) {
        Author author = modelMapper.map(authorRequest, Author.class);
        Author savedAuthor = authorRepository.save(author);
        return savedAuthor.getId();
    }

    /**
     * Update an existing author.
     *
     * @param id            the ID of the author to update
     * @param authorRequest the AuthorRequest object containing updated author information
     * @throws IllegalArgumentException if no author is found with the given ID
     */
    @Override
    @Transactional
    public void update(Long id, AuthorRequest authorRequest) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()) {
            Author author = existingAuthor.get();
            modelMapper.map(authorRequest, author);
            authorRepository.save(author);
        } else {
            throw new ResourceNotFoundException(Translator.toLocale(MessagesConstants.AUTHOR_NOT_FOUND_ERROR)+id);
        }
    }

    /**
     * Delete an author by ID.
     *
     * @param id the ID of the author to delete
     * @throws IllegalArgumentException if no author is found with the given ID
     */
    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()) {
            authorRepository.delete(existingAuthor.get());
        } else {
            throw new ResourceNotFoundException(Translator.toLocale(MessagesConstants.AUTHOR_NOT_FOUND_ERROR)+id);
        }
    }

}
