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


import java.text.Format;
import java.time.LocalDate;
import java.util.*;

public class Library {

    Scanner scanner = new Scanner(System.in);
    Formatter fmt = new Formatter();

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

    // Método para añadir algunos autores y libros automáticamente a la base de datos.
    public void temporaryTestMethod() {

        Author a = new Author("J.K. Rowling", "j.k.rowling@email.com");
        Author a1 = new Author("J.R.R. Tolkien", "tolkien@gmail.com");
        Author a2 = new Author("Miguel de Cervantes", "- sin email -");

        Book b = new Book("123456", "Harry Potter y la piedra filosofal", "Fantasía", 6, a);
        Book b1 = new Book("64312", "Harry Potter y la cámara de los secretos", "Fantasía", 4, a);
        Book b2 = new Book("963258", "Silmarillion", "Fantasía Épica", 3, a1);
        Book b3 = new Book("741852", "La Galatea", "Novela pastoril", 15, a2);
        Book b4 = new Book("147256", "Don Quijote de la Mancha", "Sátira", 4, a2);


        authorRepository.saveAll(List.of(a, a1, a2));
        bookRepository.saveAll(List.of(b, b1, b2, b3, b4));

    }

    // Método para imprimir el menú
    public void displayOptions() {

        System.out.println("""

                1. Add a book
                2. Search book by title
                3. Search book by category
                4. Search book by Author
                5. List all books along with author
                6. Issue book to student
                7. List books by usn
                8. Display books to be returned today
                9. Exit                         
                """);

    }

    // Método para seleccionar las opciones del menú
    public void menu() {

        short menu;

        while (true) {

            // Imprimimos menú
            displayOptions();

            // Validamos que los datos introducidos sean de tipo short
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
                    booksThatNeedToBeReturnedToday();
                    break;
                case 9:
                    System.out.println("The program is over");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter a valid option");
                    break;

            }

        }

    }

    // Método para añadir un libro
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

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            isbn = scanner.nextLine();

            if (isbn.isEmpty()) {

                System.out.println("Insert a valid ISBN!");

            } else {

                break;

            }

        }

        System.out.println("Enter title");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            title = scanner.nextLine();

            if (title.isEmpty()) {

                System.out.println("Insert a valid title!");

            } else {

                break;

            }

        }

        System.out.println("Enter category");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            category = scanner.nextLine();

            if (category.isEmpty()) {

                System.out.println("Insert a valid category!");

            } else {

                break;

            }

        }

        System.out.println("Enter number of books");

        // Validamos que los datos introducidos no sean vacíos
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

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            authorName = scanner.nextLine();

            if (authorName.isEmpty()) {

                System.out.println("Insert a valid author name!");

            } else {

                break;

            }

        }

        System.out.println("Enter Author email");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            authorEmail = scanner.nextLine();

            if (authorEmail.isEmpty()) {

                System.out.println("Insert a valid author email!");

            } else {

                break;

            }

        }

        // Guardamos en el repositorio el autor y el libro
        author = authorRepository.save(new Author(authorName, authorEmail));
        bookRepository.save(new Book(isbn, title, category, quantity, author));

        System.out.println("Author saved into DB!\nBook saved into DB!");

    }

    // Método para buscar libro por su título
    public void searchBookByTitle() {

        Scanner scanner = new Scanner(System.in);
        String bookName;

        System.out.println("Enter a book name");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            bookName = scanner.nextLine();

            if (bookName.isEmpty()) {

                System.out.println("Insert a valid book name!");

            } else {

                break;

            }

        }

        //Hacemos el loop y no directamente el toString de la Lista para el formato del sout de cada libro
        printBook(bookRepository.findByTitle(bookName));

    }

    // Método para buscar libro por u categoría
    public void searchBookByCategory() {

        Scanner scanner = new Scanner(System.in);
        String bookCategory;

        System.out.println("Enter a book category");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            bookCategory = scanner.nextLine();

            if (bookCategory.isEmpty()) {

                System.out.println("Insert a valid book category!");

            } else {

                break;

            }

        }

        //Hacemos el loop y no directamente el toString de la Lista para el formato del sout de cada libro
        printBook(bookRepository.findByCategory(bookCategory));

    }

    // Método para buscar liobro por su autor
    public void searchBookByAuthor() {

        Scanner scanner = new Scanner(System.in);
        String authorName;

        System.out.println("Enter an Author Name");

        // Validamos que los datos introducidos no sean vacíos
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
        if (!authorList.isEmpty()) {
            Formatter fmt = new Formatter();
            fmt.format("%15s %14s %14s %15s %15s %15s\n", "Book ISBN", "Book Title", "Category", "No of Books", "Author name", "Author mail");
            for (Author author : authorList) {
                for (int j = 0; j < author.getAuthorBook().size(); j++) {
                    fmt = author.getAuthorBook().get(j).toStringListWithAuthor(fmt);
                }
            }
            System.out.println(fmt);
        } else {
            System.out.println("This author has no books");
        }
    }

    // Método para listar todos los libros con autor
    public void listAllBooksWithAuthor() {

        List<Book> bookList = bookRepository.findAll();
        Formatter fmt = new Formatter();
        fmt.format("%15s %14s %14s %15s %15s %15s\n", "Book ISBN", "Book Title", "Category", "No of Books", "Author name", "Author mail");
        if (!bookList.isEmpty()) {
            for (Book book : bookList) {
                fmt = book.toStringListWithAuthor(fmt);
            }
            System.out.println(fmt);
        } else {
            System.out.println("There is any book");
        }

    }

    // Método para buscar los libros prestados a los estudiantes
    public void issueBookToStudent() {

        Scanner scanner = new Scanner(System.in);
        Student student;
        Book book;
        Issue issue;
        String studentUsn;
        String studentName;
        String isbn;

        System.out.println("Enter The Student's USN");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            studentUsn = scanner.nextLine();

            if (studentUsn.isEmpty()) {

                System.out.println("Insert a valid student USN!");

            } else {

                break;

            }

        }

        System.out.println("Enter The Student's Name");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            studentName = scanner.nextLine();

            if (studentName.isEmpty()) {

                System.out.println("Insert a valid student name!");

            } else {

                break;

            }

        }

        // Buscamos estudiante por ID, si lo encontramos lo guardamos en una variable, si no, lo creamos y lo guardamos
        // en la base de datos
        if (studentRepository.findById(studentUsn).isPresent()) {
            student = studentRepository.findById(studentUsn).get();
        } else {
            student = new Student(studentUsn, studentName);
            studentRepository.save(student);
        }

        System.out.println("Enter book ISBN");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            isbn = scanner.nextLine();

            if (isbn.isEmpty()) {

                System.out.println("Insert a valid isbn!");

            } else {

                break;

            }

        }

        // Buscamos el libro por su ISBN
        if (bookRepository.findById(isbn).isPresent()) {

            book = bookRepository.findById(isbn).get();

            // Si no hay libros disponibles avisamos, sino lo restamos de la cantidad que haya disponible
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

    // Método para imprimir todos los libros
    public void printBook(List<Book> bookList) {

        if (!bookList.isEmpty()) {

            Formatter fmt = new Formatter();

            fmt.format("%15s %14s %14s %15s\n", "Book ISBN", "Book Title", "Category", "No of Books");

            for (Book book : bookList) {

                fmt = book.toStringSimplified(fmt);

            }

            System.out.println(fmt);

        } else {

            System.out.println("Books not found");

        }

    }

    // Método para listar todos los libros por USN
    public void listBooksByUsn() {

        Scanner scanner = new Scanner(System.in);

        Student student;

        List<Issue> issueList;

        Book book;

        System.out.println("Enter the USN");
        String usn = scanner.nextLine();

        // Comprobamos que exista el USN
        if (studentRepository.findById(usn).isPresent()) {

            student = studentRepository.findById(usn).get();

            issueList = student.getissueList();

            // Si no hay ningún issue lo mostramos y salimos
            if (issueList.isEmpty()) {
                System.out.println("There isn't any USN saved yet!");
                return;
            }

            // todo: EDU MIRAR ESTO PARA QUE IMPRIMA BIEN
            fmt.format("%15s %14s %14s\n","Book Title","Student Name","Return date");

//            System.out.println("Book Title" + "\t" + "Student Name" + "\t" + "\t" + "Return date");

            // Por cada issue que haya por ese USN imprimimos el nombre del titulo, el nombre del estudiante y la fecha retorno
            for (Issue issue : issueList) {

                book = bookRepository.findById(issue.getIssueBook().getIsbn()).get();
                fmt.format("%14s %14s %14s\n", book.getTitle(),student.getName(),issue.getReturnDate());

            }
            System.out.println(fmt);

        } else {

            System.err.println("The USN doesn't match.");

        }

    }

    // Método BONUS para devolver lista de libros que se tienen que devolver hoy
    public void booksThatNeedToBeReturnedToday() {

        List<Issue> issueList;
        List<Book> booksThatNeedToBeReturnedToday = new ArrayList<>();

        issueList = issueRepository.findAll();

        for (Issue issue : issueList) {

            if (issue.getReturnDate().equals(LocalDate.now())) {

                booksThatNeedToBeReturnedToday.add(issueRepository.findById(issue.getIssueId()).get().getIssueBook());

            }

        }

        if (booksThatNeedToBeReturnedToday.isEmpty()) {

            System.out.println("There are any books that need to be returned today!");

        } else {

            for (int i = 0; i < booksThatNeedToBeReturnedToday.size(); i++) {

                System.out.println((i+1) + ". " +  booksThatNeedToBeReturnedToday.get(i).getTitle());

            }

        }

    }

}
