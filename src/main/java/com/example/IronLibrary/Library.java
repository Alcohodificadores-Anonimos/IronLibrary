package com.example.IronLibrary;

import com.example.IronLibrary.models.Author;
import com.example.IronLibrary.models.Book;
import com.example.IronLibrary.models.Issue;
import com.example.IronLibrary.models.Student;
import com.example.IronLibrary.repositories.AuthorRepository;
import com.example.IronLibrary.repositories.BookRepository;
import com.example.IronLibrary.repositories.IssueRepository;
import com.example.IronLibrary.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDate;
import java.util.List;
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

        Book b = new Book("123456","title","category1",100,a);
        Book b1 = new Book("64312","title1","category1",10,a1);
        Book b2 = new Book("963258","title2","category2",50,a2);
        Book b3 = new Book("741852","title3","category3",75,a2);
        Book b4 = new Book("147256","title4","category4",20,a2);

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
                    searchBookByCategory();
                    break;
                case 4:
                    searchBookByAuthor();
                    break;
                case 5:
                    listAllBooksWithAuthor();
                    break;
                case 6:
                    issueBookToStudent();
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

        System.out.println("Book ISBN"+ "\t" + "Book Title" + "\t" + "Category" + "\t" + "No of Books");

        List<Book> bookList = bookRepository.findByTitle(bookName);

        //Hacemos el loop y no directamente el toString de la Lista para el formato del sout de cada libro
        if(!bookList.isEmpty()) {
            for (Book book : bookList) {
                System.out.println(book.toStringSimplified());
            }
        }else{
            System.out.println("There are no books with that name");
        }

    }

    public void searchBookByCategory(){
        Scanner scanner = new Scanner(System.in);
        String bookCategory;


        System.out.println("Enter a book category");
        bookCategory = scanner.nextLine();

        List<Book> bookList = bookRepository.findByCategory(bookCategory);

        //Hacemos el loop y no directamente el toString de la Lista para el formato del sout de cada libro
        System.out.println("Book ISBN"+ "\t" + "Book Title" + "\t" + "Category" + "\t" + "No of Books");
        if(!bookList.isEmpty()){
            for (Book book : bookList) {
                System.out.println(book.toStringSimplified());
            }
        }else{
            System.out.println("There are no books in that category");
        }
    }

    public void searchBookByAuthor(){
        Scanner scanner = new Scanner(System.in);
        String authorName;

        System.out.println("Enter an Author Name");
        authorName = scanner.nextLine();

        List<Author> authorList = authorRepository.findByName(authorName);

        //Hacemos el loop y no directamente el toString de la Lista para el formato del sout de cada libro
        System.out.println("Book ISBN"+ "\t" + "Book Title" + "\t" + "Category" + "\t" + "No of Books"+ "\t" +"Author name"+ "\t"+"\t" +"Author mail");
        if(!authorList.isEmpty()){

            for (int i = 0; i < authorList.size(); i++) {
                for (int j = 0; j < authorList.get(i).getAuthorBook().size(); j++) {
                    System.out.println(authorList.get(i).getAuthorBook().get(j).toStringWithAuthor());
                }

            }

        }else{
            System.out.println("This author has no books");
        }
    }


    public void listAllBooksWithAuthor (){

        List<Book> bookList = bookRepository.findAll();
        System.out.println("Book ISBN"+ "\t" + "Book Title" + "\t" + "Category" + "\t" + "No of Books"+ "\t" +"Author name"+ "\t"+"\t" +"Author mail");
        if(!bookList.isEmpty()){
            for (Book book: bookList) {
                System.out.println(book.toStringWithAuthor());
            }
        }else{
            System.out.println("There is any book");
        }

    }

    public void issueBookToStudent(){
        Scanner scanner = new Scanner(System.in);
        Student student;
        Book book;
        Issue issue;
        String studentUsn;
        String studentName;
        String isbn;

        System.out.println("Enter The Student's USN");
        studentUsn = scanner.nextLine();
        System.out.println("Enter The Student's Name");
        studentName = scanner.nextLine();
        if(studentRepository.findById(studentUsn).isPresent()){
            student = studentRepository.findById(studentUsn).get();
        }else{
            student = new Student(studentUsn,studentName);
            studentRepository.save(student);
        }
        System.out.println("Enter book ISBN");
        isbn = scanner.nextLine();

        //CONTROLAR QUE EL LIBRO ESTE SINO PETA EL PROGRAMA
        book = bookRepository.findById(isbn).orElseThrow(() -> new IllegalArgumentException("The is any book with that ISBN"));

        issue = new Issue(LocalDate.now(),  LocalDate.now().plusDays( 7 ),student,book);

        issueRepository.save(issue);

        System.out.println("Book issued. Return date : " + LocalDate.now().plusDays( 7 ));



    }
    public List<Book> listBooksByUsn(String usn){
        return null;
    }

}
