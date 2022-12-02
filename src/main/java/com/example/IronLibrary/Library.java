package com.example.IronLibrary;

import com.example.IronLibrary.models.Author;
import com.example.IronLibrary.models.Book;
import com.example.IronLibrary.models.Issue;
import com.example.IronLibrary.models.Student;
import com.example.IronLibrary.repositories.AuthorRepository;
import com.example.IronLibrary.repositories.BookRepository;
import com.example.IronLibrary.repositories.IssueRepository;
import com.example.IronLibrary.repositories.StudentRepository;
import jakarta.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.util.ArrayList;
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

        Author a = new Author("a", "email");
        Author a1 = new Author("a1", "mail1");
        Author a2 = new Author("a2", "mail2");

        Book b = new Book("123456", "title", "category1", 100, a);
        Book b1 = new Book("64312", "title1", "category1", 1, a1);
        Book b2 = new Book("963258", "title2", "category2", 50, a2);
        Book b3 = new Book("741852", "title3", "category3", 75, a2);
        Book b4 = new Book("147256", "title4", "category4", 20, a2);

        authorRepository.saveAll(List.of(a, a1, a2));
        bookRepository.saveAll(List.of(b, b1, b2, b3, b4));

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

        while (true) {
            displayOptions();
            while (true) {
                if (!scanner.hasNextShort()) {
                    System.err.println("Select an option (1 to 8): ");
                    scanner.next();
                } else {
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
                    listBooksByUsn();
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
        String isbn;
        String title;
        String category;
        int quantity;
        String authorName;
        String authorEmail;

        System.out.println("Enter isbn");

        while (true) {

            isbn = scanner.nextLine();

            if (isbn.isEmpty()) {

                System.out.println("Insert a valid ISBN!");

            } else {

                break;

            }

        }

        System.out.println("Enter title");

        while (true) {

            title = scanner.nextLine();

            if (title.isEmpty()) {

                System.out.println("Insert a valid title!");

            } else {

                break;

            }

        }

        System.out.println("Enter category");

        while (true) {

            category = scanner.nextLine();

            if (category.isEmpty()) {

                System.out.println("Insert a valid category!");

            } else {

                break;

            }

        }

        System.out.println("Enter number of books");

        while (true) {

            if (!scanner.hasNextInt()) {

                System.out.println("Enter a number!");
                scanner.next();

            } else {

                quantity = scanner.nextInt();

                if (quantity > 0) {

                    break;

                } else {

                    System.out.println("The number must be positive!");

                }

            }

        }

        System.out.println("Enter Author name");
        authorName = scanner.nextLine();

        while (true) {

            authorName = scanner.nextLine();

            if (authorName.isEmpty()) {

                System.out.println("Insert a valid author name!");

            } else {

                break;

            }

        }

        System.out.println("Enter Author email");

        while (true) {

            authorEmail = scanner.nextLine();

            if (authorEmail.isEmpty()) {

                System.out.println("Insert a valid author email!");

            } else {

                break;

            }

        }

        author = authorRepository.save(new Author(authorName, authorEmail));
        bookRepository.save(new Book(isbn, title, category, quantity, author));

        System.out.println("Author saved into DB!\nBook saved into DB!");

    }

    public void searchBookByTitle() {

        Scanner scanner = new Scanner(System.in);
        String bookName;

        System.out.println("Enter a book name");

        while (true) {

            bookName = scanner.nextLine();

            if (bookName.isEmpty()) {

                System.out.println("Insert a valid book name!");

            } else {

                break;

            }

        }

        System.out.println("Book ISBN" + "\t" + "Book Title" + "\t" + "Category" + "\t" + "No of Books");

        List<Book> bookList = bookRepository.findByTitle(bookName);

        //Hacemos el loop y no directamente el toString de la Lista para el formato del sout de cada libro
        if (!bookList.isEmpty()) {
            for (Book book : bookList) {
                System.out.println(book.toStringSimplified());
            }
        } else {
            System.out.println("There are no books with that name");
        }

    }

    public void searchBookByCategory() {

        Scanner scanner = new Scanner(System.in);
        String bookCategory;

        System.out.println("Enter a book category");

        while (true) {

            bookCategory = scanner.nextLine();

            if (bookCategory.isEmpty()) {

                System.out.println("Insert a valid book category!");

            } else {

                break;

            }

        }

        List<Book> bookList = bookRepository.findByCategory(bookCategory);

        //Hacemos el loop y no directamente el toString de la Lista para el formato del sout de cada libro
        System.out.println("Book ISBN" + "\t" + "Book Title" + "\t" + "Category" + "\t" + "No of Books");
        if (!bookList.isEmpty()) {
            for (Book book : bookList) {
                System.out.println(book.toStringSimplified());
            }
        } else {
            System.out.println("There are no books in that category");
        }

    }

    public void searchBookByAuthor() {

        Scanner scanner = new Scanner(System.in);
        String authorName;

        System.out.println("Enter an Author Name");

        while (true) {

            authorName = scanner.nextLine();

            if (authorName.isEmpty()) {

                System.out.println("Insert a valid author name!");

            } else {

                break;

            }

        }

        List<Author> authorList = authorRepository.findByName(authorName);

        //Hacemos el loop y no directamente el toString de la Lista para el formato del sout de cada libro
        System.out.println("Book ISBN" + "\t" + "Book Title" + "\t" + "Category" + "\t" + "No of Books" + "\t" + "Author name" + "\t" + "\t" + "Author mail");
        if (!authorList.isEmpty()) {

            for (Author author : authorList) {
                for (int j = 0; j < author.getAuthorBook().size(); j++) {
                    System.out.println(author.getAuthorBook().get(j).toStringWithAuthor());
                }

            }

        } else {
            System.out.println("This author has no books");
        }
    }


    public void listAllBooksWithAuthor() {

        List<Book> bookList = bookRepository.findAll();
        System.out.println("Book ISBN" + "\t" + "Book Title" + "\t" + "Category" + "\t" + "No of Books" + "\t" + "Author name" + "\t" + "\t" + "Author mail");
        if (!bookList.isEmpty()) {
            for (Book book : bookList) {
                System.out.println(book.toStringWithAuthor());
            }
        } else {
            System.out.println("There is any book");
        }

    }

    public void issueBookToStudent() {

        Scanner scanner = new Scanner(System.in);
        Student student;
        Book book;
        Issue issue;
        String studentUsn;
        String studentName;
        String isbn;

        System.out.println("Enter The Student's USN");

        while (true) {

            studentUsn = scanner.nextLine();

            if (studentUsn.isEmpty()) {

                System.out.println("Insert a valid student USN!");

            } else {

                break;

            }

        }

        System.out.println("Enter The Student's Name");

        while (true) {

            studentName = scanner.nextLine();

            if (studentName.isEmpty()) {

                System.out.println("Insert a valid student name!");

            } else {

                break;

            }

        }

        if (studentRepository.findById(studentUsn).isPresent()) {
            student = studentRepository.findById(studentUsn).get();
        } else {
            student = new Student(studentUsn, studentName);
            studentRepository.save(student);
        }

        System.out.println("Enter book ISBN");

        while (true) {

            isbn = scanner.nextLine();

            if (isbn.isEmpty()) {

                System.out.println("Insert a valid isbn!");

            } else {

                break;

            }

        }

        if (bookRepository.findById(isbn).isPresent()) {

            book = bookRepository.findById(isbn).get();


            if (book.getQuantity() <= 0) {
                System.err.println("No hay libros disponibles.");
            } else {
                book.setQuantity(book.getQuantity() - 1);

                bookRepository.save(book);

                issue = new Issue(LocalDate.now(), LocalDate.now().plusDays(7), student, book);

                issueRepository.save(issue);

                System.out.println("Book issued. Return date : " + LocalDate.now().plusDays(7));
            }


        } else {

            System.err.println("There is any book with that ISBN!");

        }

    }

    // todo: Test
    public void listBooksByUsn() {

        Scanner scanner = new Scanner(System.in);

        Student student;

        List<Issue> issueList;

        Book book;

        System.out.println("Enter the USN");
        String usn = scanner.nextLine();

        if (studentRepository.findById(usn).isPresent()) {

            student = studentRepository.findById(usn).get();

            issueList = student.getissueList();

            if (issueList.isEmpty()) {
                System.out.println("There isn't any USN saved yet!");
                return;
            }

            // todo: EDU MIRAR ESTO PARA QUE IMPRIMA BIEN

            System.out.println("Book Title" + "\t" + "Student Name" + "\t" + "\t" + "Return date");

            for (Issue issue : issueList) {

                book = bookRepository.findById(issue.getIssueBook().getIsbn()).get();

                System.out.println(book.getTitle() + "\t" + "\t" + student.getName() + "\t" + "\t" + issue.getReturnDate());

            }

        } else {
            System.err.println("The USN doesn't match.");
        }
    }

}
