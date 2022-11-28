package com.example.IronLibrary;

import com.example.IronLibrary.models.Author;
import com.example.IronLibrary.models.Book;
import com.example.IronLibrary.repositories.AuthorRepository;
import com.example.IronLibrary.repositories.BookRepository;
import com.example.IronLibrary.repositories.IssueRepository;
import com.example.IronLibrary.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

public class Library {

    Scanner scanner = new Scanner(System.in);

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    IssueRepository issueRepository;


    public void displayOptions() {

        System.out.println("1. Add a book");
        System.out.println("2. Search book by title");
        System.out.println("3. Search book by category");
        System.out.println("4. Search book by Author");
        System.out.println("5. List all books along with author");
        System.out.println("6. Issue book to student");
        System.out.println("7. List books by usn");
        System.out.println("8. Exit");
    }

    public void menu() {

        short menu;


        while(true) {
            displayOptions();
            while (true) {
                if (!scanner.hasNextShort()) {
                    System.err.println("Insert a number");
                    scanner.next();
                }else{
                    menu = scanner.nextShort();
                    break;
                }
            }
            switch (menu) {
                case 1:

                    break;
                case 2:
                    searchBookByTitle();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("The program is over");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter a valid option");
                    break;
            }
        }
    }

    public void addBook(){
/*        String name;
        String ibs;
        int pages;
        String authorString;
        Author author = null;
        //name = in
        for(Author a : authorRepository.findAll()){
            if(authorString.equals(a.getName())){
                author = a;
            }
        }
        new Book();
        author.getAuthorBook(book);*/
    }

    public void searchBookByTitle() {

        Scanner scanner = new Scanner(System.in);
        String bookName;

        System.out.println("Enter a book name");
        bookName = scanner.nextLine();

        for(Book b : bookRepository.findAllByTitle(bookName)){
            System.out.println(b);
        }
    }

    public void searchBookByCategory(){

    }

    public void searchBookByAuthor(){

    }
    public void listAllbookOfAuthor(){

    }

    public void issueBookToStudent(){

    }
    public void listBooksByUsn(){

    }



}
