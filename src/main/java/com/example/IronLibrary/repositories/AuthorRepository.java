package com.example.IronLibrary.repositories;

import com.example.IronLibrary.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByAuthorBookIsbn();
}
