package com.example.IronLibrary;

import com.example.IronLibrary.models.Author;
import com.example.IronLibrary.models.Book;
import com.example.IronLibrary.models.Issue;
import com.example.IronLibrary.models.Student;
import com.example.IronLibrary.repositories.AuthorRepository;
import com.example.IronLibrary.repositories.BookRepository;
import com.example.IronLibrary.repositories.IssueRepository;
import com.example.IronLibrary.repositories.StudentRepository;
import org.junit.jupiter.api.AfterEach;
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

	@AfterEach
	public void tearDown(){
		issueRepository.deleteAll();
		bookRepository.deleteAll();
		studentRepository.deleteAll();
		authorRepository.deleteAll();
	}

	@Test
	void contextLoads() {
		System.out.println("LIBROSSSS" + author.getAuthorBook().size());
	}

	@Test
	void findByTitle() {
		assertEquals("Don Quijote",bookRepository.findByTitle("Don Quijote").get(0).getTitle());
	}
	@Test
	void findByCategory(){
		assertEquals("Don Quijote",bookRepository.findByCategory("novela").get(0).getTitle());
		assertEquals("novela",bookRepository.findByCategory("novela").get(0).getCategory());
		assertEquals("ISBN1", bookRepository.findByCategory("novela").get(0).getIsbn());
		assertEquals(1000, bookRepository.findByCategory("novela").get(0).getQuantity());
	}
	@Test
	void findByAuthor(){
			assertEquals("Novelas Ejemplares",authorRepository.findByName("Cervantes").get(0).getAuthorBook().get(1).getTitle());
			assertEquals("novela",authorRepository.findByName("Cervantes").get(0).getAuthorBook().get(1).getCategory());
			assertEquals("Don Quijote",authorRepository.findByName("Cervantes").get(0).getAuthorBook().get(0).getTitle());
			assertEquals("ISBN1",authorRepository.findByName("Cervantes").get(0).getAuthorBook().get(0).getIsbn());

	}

	@Test
	void listAllBooksWithAuthor(){
		assertEquals("novela", bookRepository.findAll().get(0).getCategory());
		assertEquals("ISBN2", bookRepository.findAll().get(1).getIsbn());
		assertEquals("Cervantes", bookRepository.findAll().get(1).getAuthor().getName());
		assertEquals(2, bookRepository.findAll().get(0).getIssueBookId().getIssueId());
	}
	@Test
	void issueBookToStudent(){
		assertEquals(1, issueRepository.findById(1).get().getIssueId());
		assertEquals("Manu", issueRepository.findById(1).get().getIssueStudent().getName());
		assertEquals("ISBN1", issueRepository.findById(1).get().getIssueBook().getIsbn());
		assertEquals(LocalDate.of(2022,12,5), issueRepository.findById(1).get().getReturnDate());
		assertEquals(LocalDate.of(2022,11,30), issueRepository.findById(1).get().getIssueDate());
	}

	@Test
	void listBooksByUsn(){
		assertEquals("Don Quijote",studentRepository.findById("USN1").get().getissueList().get(0).getIssueBook().getTitle());
	}
}
