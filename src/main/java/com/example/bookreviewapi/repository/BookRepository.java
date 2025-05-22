package com.example.bookreviewapi.repository;

import com.example.bookreviewapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);  // <--- Add this line
    //List<Book> fingById(Long id);
}
