package com.example.bookmanagement.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.bookmanagement.entities.Book;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.repositories.BookRepository;
import com.example.bookmanagement.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Save a new book or update an existing one.
     *
     * @param book the book to save or update
     * @return the saved or updated book
     */
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Update an existing book.
     *
     * @param id          the ID of the book to update
     * @param bookDetails the updated details of the book
     * @return the updated book
     * @throws ResourceNotFoundException if no book is found with the given ID
     */
    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));

        book.setTitle(bookDetails.getTitle());
        book.setPublishedDate(bookDetails.getPublishedDate());
        book.setGenre(bookDetails.getGenre());
        book.setDescription(bookDetails.getDescription());
        book.setAuthor(bookDetails.getAuthor());

        return bookRepository.save(book);
    }

    /**
     * Delete a book by ID.
     *
     * @param id the ID of the book to delete
     * @throws ResourceNotFoundException if no book is found with the given ID
     */
    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));
        bookRepository.delete(book);
    }

    /**
     * Retrieve all books with pagination.
     *
     * @param pageable pagination information
     * @return a page of books
     */
    @Override
    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    /**
     * Retrieve a book by title.
     *
     * @param title the title of the book to retrieve
     * @return an optional containing the book, or empty if not found
     */
    @Override
    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
