package com.example.IronLibrary.repositories;

import com.example.IronLibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByTitle(String title);
    List<Book> findByCategory(String category);
    List<Book> findByAuthor (String author);

}
