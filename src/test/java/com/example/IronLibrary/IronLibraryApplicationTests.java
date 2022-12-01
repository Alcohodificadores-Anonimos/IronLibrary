package com.example.IronLibrary;

import com.example.IronLibrary.models.Author;
import com.example.IronLibrary.models.Book;
import com.example.IronLibrary.models.Issue;
import com.example.IronLibrary.models.Student;
import com.example.IronLibrary.repositories.AuthorRepository;
import com.example.IronLibrary.repositories.BookRepository;
import com.example.IronLibrary.repositories.IssueRepository;
import com.example.IronLibrary.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class IronLibraryApplicationTests {

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	IssueRepository issueRepository;
	Author author;
	Book book1, book2;
	Student student;
	Issue issue;

	@BeforeEach
	public void setUp(){

		author = new Author("Cervantes", "cervantes@mail.com");
		authorRepository.save(author);

		book1 = new Book("ISBN1", "Don Quijote", "novela", 1000, author);
		book2 = new Book("ISBN2", "Novelas Ejemplares", "novela", 500,author);
		bookRepository.save(book1);
		bookRepository.save(book2);

		student = new Student("USN1", "Manu");
		studentRepository.save(student);

		issue = new Issue(LocalDate.of(2022, 11 ,30),LocalDate.of(2022,12,5),student,book1);
		issueRepository.save(issue);

	}

	@Test
	void contextLoads() {
		System.out.println("LIBROSSSS" + author.getAuthorBook().size());
	}

	@Test
	void findByTitle() {
		assertEquals("Don Quijote",bookRepository.findByTitle("Don Quijote").get(0).getTitle());
	}

}
