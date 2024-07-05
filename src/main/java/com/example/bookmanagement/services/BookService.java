package com.example.bookmanagement.services;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.bookmanagement.entities.Book;

@Service
public interface BookService {
	Book saveBook(Book book);
    Book updateBook(Long id, Book bookDetails);
    void deleteBook(Long id);
    Page<Book> getBooks(Pageable pageable);
    Optional<Book> getBookByTitle(String title);

}
