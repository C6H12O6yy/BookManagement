package com.example.bookmanagement.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmanagement.configs.Constants;
import com.example.bookmanagement.entities.Book;
import com.example.bookmanagement.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;

	
	/** 
	 * @param book
	 * @return ResponseEntity<Book>
	 */
	@PostMapping
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
		Book savedBook = bookService.saveBook(book);
		return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}

	
	/** 
	 * @param id
	 * @param bookDetails
	 * @return ResponseEntity<Book>
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id,@Valid @RequestBody Book bookDetails) {
		Book updatedBook = bookService.updateBook(id, bookDetails);
		return ResponseEntity.ok(updatedBook);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<Page<Book>> getAllBooks(@RequestParam(defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Book> books = bookService.getBooks(pageable);
		return ResponseEntity.ok(books);
	}

	@GetMapping("/title")
	public ResponseEntity<Book> getBookByTitle(@RequestParam String title) {
		Optional<Book> book = bookService.getBookByTitle(title);
		return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
