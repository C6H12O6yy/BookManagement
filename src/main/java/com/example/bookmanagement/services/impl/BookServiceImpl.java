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
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;

	
	/** 
	 * @param book
	 * @return Book
	 */
	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	
	/** 
	 * @param id
	 * @param bookDetails
	 * @return Book
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

	@Override
	public void deleteBook(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));
		bookRepository.delete(book);
	}

	@Override
	public Page<Book> getBooks(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	@Override
	public Optional<Book> getBookByTitle(String title) {
		return bookRepository.findByTitle(title);
	}
}
