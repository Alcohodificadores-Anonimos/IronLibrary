package com.example.IronLibrary;

import com.example.IronLibrary.repositories.AuthorRepository;
import com.example.IronLibrary.repositories.BookRepository;
import com.example.IronLibrary.repositories.IssueRepository;
import com.example.IronLibrary.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IronLibraryApplication implements CommandLineRunner {

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	IssueRepository issueRepository;
	@Autowired
	StudentRepository studentRepository;


	public static void main(String[] args) {
		SpringApplication.run(IronLibraryApplication.class, args);
	}

	@Override
	public void run(String... args){

		Library library = new Library(authorRepository, bookRepository,studentRepository, issueRepository);
		library.temporaryTestMethod();
		library.menu();
	}

}
