package com.example.IronLibrary;

import com.example.IronLibrary.models.Author;
import com.example.IronLibrary.models.Book;
import com.example.IronLibrary.repositories.AuthorRepository;
import com.example.IronLibrary.repositories.BookRepository;
import com.example.IronLibrary.repositories.IssueRepository;
import com.example.IronLibrary.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Library {

    Scanner scanner = new Scanner(System.in);

    public Library(AuthorRepository authorRepository, BookRepository bookRepository, StudentRepository studentRepository, IssueRepository issueRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.issueRepository = issueRepository;
    }

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    IssueRepository issueRepository;

    public void temporaryTestMethod() {
        Author a = new Author("a","email");
        Author a1 = new Author("a1","mail1");
        Author a2 = new Author("a2","mail2");

        Book b = new Book("isbn","title","category",1,a);
        Book b1 = new Book("isbn1","title1","category1",1,a1);
        Book b2 = new Book("isbn2","title2","category2",1,a2);
        Book b3 = new Book("isbn3","title3","category3",1,a2);
        Book b4 = new Book("isbn4","title4","category4",1,a2);

        authorRepository.saveAll(List.of(a,a1,a2));
        bookRepository.saveAll(List.of(b,b1,b2,b3,b4));
    }

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
                    System.err.println("Select an option (1 to 8): ");
                    scanner.next();
                }else{
                    menu = scanner.nextShort();
                    break;
                }
            }
            switch (menu) {
                case 1:
                    addBook();
                    break;
                case 2:
                    searchBookByTitle();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Type the author's Id: ");
                    Integer id = scanner.nextInt();
                    Author author = authorRepository.findById(id).get();
                    System.out.println(listAllBooksOfAuthor(author));
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

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        Author author;
        Book book;
        String isbn;
        String title;
        String category;
        int quantity;
        String authorName;
        String authorEmail;


        System.out.println("Enter isbn");
        isbn = scanner.nextLine();
        System.out.println("Enter title");
        title = scanner.nextLine();
        System.out.println("Enter category");
        category = scanner.nextLine();
        System.out.println("Enter number of books");
        quantity = scanner.nextInt();
        System.out.println("Enter Author name");
        //pongo 2 sino el scanner hace cosas raras
        authorName = scanner.nextLine();
        authorName = scanner.nextLine();
        System.out.println("Enter Author mail");
        authorEmail = scanner.nextLine();

        author = authorRepository.save(new Author(authorName,authorEmail));
        book = bookRepository.save(new Book(isbn, title, category,quantity, author));

        System.out.println("Author saved into DB!");
        System.out.println("Book saved into DB!");


    }


    /*    for(Author a : authorRepository.findAll()){
            if(authorName.equals(a.getName())){
                author = a;
            }
        }
        new Book();
        author.getAuthorBook(book);
    }*/

    public void searchBookByTitle() {

        Scanner scanner = new Scanner(System.in);
        String bookName;
        boolean bookFound = false;

        System.out.println("Enter a book name");
        bookName = scanner.nextLine();

        System.out.println("Book ISBN \t Book Title \t Category \t No of Books ");

        List<Book> bookList = bookRepository.findByTitle(bookName);
        if(!bookList.isEmpty()){
            System.out.println(bookList.get(0));
        }else{
            System.out.println("There are no books with that name");
        }

     /*   for(Book b : bookRepository.findAllByTitle(bookName)){

            if(b != null) {
                System.out.println(b);
                bookFound = true;
            }

        }

        if(!bookFound) System.out.println("No hay ningún libro con este nombre.");*/
    }

    public void searchBookByCategory(String category){

    }

    public void searchBookByAuthor(Author author){

    }
    public List<Book> listAllBooksOfAuthor (Author author){
        return author.getAuthorBook();
    }

    public void issueBookToStudent(){

    }
    public List<Book> listBooksByUsn(String usn){
        return null;
    }

}
