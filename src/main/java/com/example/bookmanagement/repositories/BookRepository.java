package com.example.bookmanagement.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookmanagement.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	Optional<Book> findByTitle(String title);
}
