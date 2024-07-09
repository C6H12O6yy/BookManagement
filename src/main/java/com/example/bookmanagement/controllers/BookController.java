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

import io.swagger.annotations.*;

/**
 * Controller class for handling operations related to books.
 * All endpoints in this controller are mapped under "/books".
 */
@RestController
@RequestMapping("/books")
@Api(tags = "Book Management")
public class BookController {

    @Autowired
    private IBookService bookService;

    /**
     * Create a new book.
     *
     * @param book the book to be created
     * @return a {@link ResponseEntity} containing the created {@link Book}
     */
    @ApiOperation(value = "Create a new book")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book successfully created"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Book> createBook(
            @ApiParam(value = "Book data to create", required = true) @Valid @RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    /**
     * Update an existing book.
     *
     * @param id          the ID of the book to update
     * @param bookDetails the updated details of the book
     * @return a {@link ResponseEntity} containing the updated {@link Book}
     */
    @ApiOperation(value = "Update an existing book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book successfully updated"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 404, message = "Book not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @ApiParam(value = "ID of the book to update", required = true) @PathVariable Long id,
            @ApiParam(value = "Updated book details", required = true) @Valid @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    /**
     * Delete a book by ID.
     *
     * @param id the ID of the book to delete
     * @return a {@link ResponseEntity} with an empty body
     */
    @ApiOperation(value = "Delete a book by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Book successfully deleted"),
            @ApiResponse(code = 404, message = "Book not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @ApiParam(value = "ID of the book to delete", required = true) @PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all books with pagination.
     *
     * @param page the page number, default is
     *             {@value Constants#DEFAULT_PAGE_NUMBER}
     * @param size the page size, default is {@value Constants#DEFAULT_PAGE_SIZE}
     * @return a {@link ResponseEntity} containing a page of {@link Book}
     */
    @ApiOperation(value = "Get all books with pagination")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved books"),
            @ApiResponse(code = 400, message = "Invalid pagination parameters"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookService.getBooks(pageable);
        return ResponseEntity.ok(books);
    }

    /**
     * Get a book by title.
     *
     * @param title the title of the book to retrieve
     * @return a {@link ResponseEntity} containing the {@link Book} with the
     *         specified title,
     *         or a not found response if no book is found
     */
    @ApiOperation(value = "Get a book by title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved book"),
            @ApiResponse(code = 404, message = "Book not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/title")
    public ResponseEntity<Book> getBookByTitle(
            @ApiParam(value = "Title of the book to retrieve", required = true) @RequestParam String title) {
        Optional<Book> book = bookService.getBookByTitle(title);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
