package com.example.bookmanagement.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bookmanagement.entities.Book;
import com.example.bookmanagement.services.IBookService;
import com.example.bookmanagement.utils.Constants;

/**
 * REST controller for managing books.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    /**
     * Create a new book.
     *
     * @param book the book to be created
     * @return a {@link ResponseEntity} containing the created {@link Book}
     */
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    /**
     * Update an existing book.
     *
     * @param id the ID of the book to update
     * @param bookDetails the updated details of the book
     * @return a {@link ResponseEntity} containing the updated {@link Book}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    /**
     * Delete a book by ID.
     *
     * @param id the ID of the book to delete
     * @return a {@link ResponseEntity} with an empty body
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all books with pagination.
     *
     * @param page the page number, default is {@value Constants#DEFAULT_PAGE_NUMBER}
     * @param size the page size, default is {@value Constants#DEFAULT_PAGE_SIZE}
     * @return a {@link ResponseEntity} containing a page of {@link Book}
     */
    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(@RequestParam(defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                  @RequestParam(defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookService.getBooks(pageable);
        return ResponseEntity.ok(books);
    }

    /**
     * Get a book by title.
     *
     * @param title the title of the book to retrieve
     * @return a {@link ResponseEntity} containing the {@link Book} with the specified title,
     *         or a not found response if no book is found
     */
    @GetMapping("/title")
    public ResponseEntity<Book> getBookByTitle(@RequestParam String title) {
        Optional<Book> book = bookService.getBookByTitle(title);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
