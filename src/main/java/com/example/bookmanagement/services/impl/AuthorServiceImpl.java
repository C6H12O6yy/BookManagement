package com.example.bookmanagement.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.bookmanagement.dto.request.AuthorRequest;
import com.example.bookmanagement.dto.response.AuthorResponse;
import com.example.bookmanagement.entities.Author;
import com.example.bookmanagement.repositories.AuthorRepository;
import com.example.bookmanagement.services.AuthorService;
@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<AuthorResponse> search(String keyword) {
        List<Author> authors = authorRepository.findByAuthorNameContaining(keyword);
        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<AuthorResponse> findAll(Pageable pageable) {
        Page<Author> authors = authorRepository.findAll(pageable);
        return authors.map(author -> modelMapper.map(author, AuthorResponse.class));
    }


    @Override
    public AuthorResponse get(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return modelMapper.map(author.get(), AuthorResponse.class);
        } else {
            throw new IllegalArgumentException("Cannot find author with id: " + id);
        }
    }

    @Override
    public Long create(AuthorRequest authorRequest) {
        Author author = modelMapper.map(authorRequest, Author.class);
        Author savedAuthor = authorRepository.save(author);
        return savedAuthor.getId();
    }

    @Override
    public void update(Long id, AuthorRequest authorRequest) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()) {
            Author author = existingAuthor.get();
            modelMapper.map(authorRequest, author);
            authorRepository.save(author);
        } else {
            throw new IllegalArgumentException("Cannot find author with id: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()) {
            authorRepository.delete(existingAuthor.get());
        } else {
            throw new IllegalArgumentException("Cannot find author with id: " + id);
        }
    }

}
