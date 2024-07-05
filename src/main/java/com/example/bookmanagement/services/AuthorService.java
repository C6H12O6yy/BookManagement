package com.example.bookmanagement.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.bookmanagement.dto.request.AuthorRequest;
import com.example.bookmanagement.dto.response.AuthorResponse;

public interface AuthorService {
    public Page<AuthorResponse> findAll(Pageable pageable);
    public AuthorResponse get(final Long id) ;
    public Long create(final AuthorRequest authorRequest);
    public void update(final Long id, final AuthorRequest authorRequest);
    public void delete(final Long id);
    public List<AuthorResponse> search(final String authorName);
} 