package com.example.bookmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmanagement.entities.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    List<Author> findByAuthorNameContaining(String keyword);
} 
