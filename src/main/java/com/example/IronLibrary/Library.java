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

import java.time.LocalDate;
import java.util.*;

public class Library {

    Scanner scanner = new Scanner(System.in);
    Formatter fmt = new Formatter();
    Student student;
    Book book;
    List<Issue> issueList;
    String isbn;
    String authorName;
    List<List<String>> rows = new ArrayList<>();
    final List<String> headersBookAuthor = Arrays.asList("Book ISBN", "Book Title", "Category", "No of Books", "Author name", "Author mail");
    final List<String> headersBook = Arrays.asList("Book ISBN", "Book Title", "Category", "No of Books");

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

    // Añadir algunos autores y libros automáticamente a la base de datos.
    public void temporaryTestMethod() {

        Author a = new Author("J.K. Rowling", "j.k.rowling@email.com");
        Author a1 = new Author("J.R.R. Tolkien", "tolkien@gmail.com");
        Author a2 = new Author("Miguel de Cervantes", "- sin email -");

        Book b = new Book("123456", "Harry Potter y la piedra filosofal", "Fantasía", 6, a);
        Book b1 = new Book("64312", "Harry Potter y la cámara de los secretos", "Fantasía", 4, a);
        Book b2 = new Book("963258", "Silmarillion", "Fantasía Épica", 3, a1);
        Book b3 = new Book("741852", "La Galatea", "Novela pastoril", 15, a2);
        Book b4 = new Book("147256", "Don Quijote de la Mancha", "Sátira", 4, a2);

        Student s = new Student("1","Estudiante");
        Issue i = new Issue(LocalDate.of(2022,12,12),
                LocalDate.of(2022,12,30),s,b4);

        authorRepository.saveAll(List.of(a, a1, a2));
        bookRepository.saveAll(List.of(b, b1, b2, b3, b4));
        studentRepository.save(s);
        issueRepository.save(i);

    }

    // Imprimir el menú
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

    // Seleccionar las opciones del menú
    public void menu() {

        short menu;

        while (true) {

            // Llamamos imprimir menú
            displayOptions();

            // Validamos que los datos introducidos sean de tipo short
            while (true) {
                scanner = new Scanner(System.in);
                System.out.print("Enter your choice (1 to 8): ");

                if (!scanner.hasNextShort()) {

                    scanner.next();

                } else {
                    menu = scanner.nextShort();
                    break;
                }

            }

            switch (menu) {
                case 1 -> addBook();
                case 2 -> searchBookByTitle();
                case 3 -> searchBookByCategory();
                case 4 -> searchBookByAuthor();
                case 5 -> listAllBooksWithAuthor();
                case 6 -> issueBookToStudent();
                case 7 -> listBooksByUsn();
                case 8 -> booksThatNeedToBeReturnedToday();
                case 9 -> {
                    System.out.println("The program is over");
                    System.exit(0);
                }
                default -> System.out.println("Enter a valid option");
            }

        }

    }

    // Añadir un libro
    public void addBook() {

        Author author;
        String title;
        String category;
        int quantity;
        String authorEmail;

        scanner.next();
        System.out.print("Enter isbn: ");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            isbn = scanner.nextLine();

            if (isbn.isEmpty()) {

                System.out.println("Insert a valid ISBN!");

            } else {

                break;

            }

        }

        System.out.print("Enter title: ");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            title = scanner.nextLine();

            if (title.isEmpty()) {

                System.out.println("Insert a valid title!");

            } else {

                break;

            }

        }

        System.out.print("Enter category: ");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            category = scanner.nextLine();

            if (category.isEmpty()) {

                System.out.println("Insert a valid category!");

            } else {

                break;

            }

        }

        System.out.print("Enter number of books: ");

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

        System.out.print("Enter Author name: ");
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

        System.out.print("Enter Author email: ");

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

        System.out.println("\nAuthor saved into DB!\nBook saved into DB!");

    }

    // Buscar libro por su título
    public void searchBookByTitle() {

        String bookName;

        scanner.nextLine();
        System.out.print("Enter title: ");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            bookName = scanner.nextLine();

            if (bookName.isEmpty()) {

                System.out.println("Insert a valid book title!");

            } else {

                break;

            }

        }

        printBook(bookRepository.findByTitle(bookName));

    }

    // Método para buscar libro por u categoría
    public void searchBookByCategory() {

        String bookCategory;

        scanner.nextLine();
        System.out.print("Enter category: ");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            bookCategory = scanner.nextLine();

            if (bookCategory.isEmpty()) {

                System.out.println("Insert a valid book category!");

            } else {

                break;

            }

        }

        // Hacemos el loop y no directamente el toString de la Lista para el formato del sout de cada libro
        printBook(bookRepository.findByCategory(bookCategory));

    }

    // Método para buscar liobro por su autor
    public void searchBookByAuthor() {

        scanner.nextLine();
        System.out.print("Enter name: ");

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

        // Hacemos el loop y no directamente el toString de la Lista para el formato del sout de cada libro
        if (!authorList.isEmpty()) {
            rows = new ArrayList<>();
            rows.add(headersBookAuthor);

            for (Author author : authorList) {
                for (int j = 0; j < author.getAuthorBook().size(); j++) {
                    rows.add(author.getAuthorBook().get(j).toStringListWithAuthor());
                }
            }

            System.out.println("\n"+formatAsTable(rows));
        } else {
            System.out.println("This author has no books");
        }
    }

    // Método para listar todos los libros con autor
    public void listAllBooksWithAuthor() {

        List<Book> bookList = bookRepository.findAll();
        rows = new ArrayList<>();
        rows.add(headersBookAuthor);

        if (!bookList.isEmpty()) {
            for (Book book : bookList) {
                rows.add(book.toStringListWithAuthor());
            }

            System.out.println("\n"+formatAsTable(rows));
        } else {
            System.out.println("There is any book");
        }

    }

    // Método para buscar los libros prestados a los estudiantes
    public void issueBookToStudent() {

        Issue issue;
        String studentUsn;
        String studentName;

        scanner.nextLine();
        System.out.print("Enter USN: ");

        // Validamos que los datos introducidos no sean vacíos
        while (true) {

            studentUsn = scanner.nextLine();

            if (studentUsn.isEmpty()) {

                System.out.println("Insert a valid student USN!");

            } else {

                break;

            }

        }

        System.out.print("Enter name: ");

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

        System.out.print("Enter book ISBN: ");

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

                System.out.println("\nBook issued. Return date : " + LocalDate.now().plusDays(7));

            }


        } else {

            System.err.println("There is any book with that ISBN!");

        }

    }

    // Imprimir todos los libros
    public void printBook(List<Book> bookList) {

        if (!bookList.isEmpty()) {
            rows = new ArrayList<>();
            rows.add(headersBook);

            //Hacemos el loop y no directamente el toString de la Lista para el formato del sout de cada libro
            for (Book book : bookList) {

                rows.add(book.toStringListSimplified());
            }

            System.out.println("\n"+formatAsTable(rows));

        } else {

            System.out.println("\nBooks not found");

        }

    }

    // Listar todos los libros por USN
    public void listBooksByUsn() {

        scanner.nextLine();
        System.out.print("Enter the USN: ");
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

            rows = new ArrayList<>();
            rows.add(Arrays.asList("Book Title","Student Name","Return date"));
//          fmt.format("%15s %14s %14s\n","Book Title","Student Name","Return date");

            // Por cada issue que haya por ese USN imprimimos el nombre del titulo, el nombre del estudiante y la fecha retorno
            for (Issue issue : issueList) {

                book = bookRepository.findById(issue.getIssueBook().getIsbn()).get();
//              fmt.format("%14s %14s %14s\n", book.getTitle(),student.getName(),issue.getReturnDate());
                rows.add(Arrays.asList(book.getTitle(),student.getName(),issue.getReturnDate().toString()));
            }
            System.out.println("\n"+formatAsTable(rows));

        } else {

            System.err.println("The USN doesn't match.");

        }

    }

    // Método BONUS para devolver lista de libros que se tienen que devolver hoy
    public void booksThatNeedToBeReturnedToday() {

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

    public static String formatAsTable(List<List<String>> rows) {
        int[] maxLengths = new int[rows.get(0).size()];
        for (List<String> row : rows)
        {
            for (int i = 0; i < row.size(); i++)
            {
                maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
            }
        }

        StringBuilder formatBuilder = new StringBuilder();
        for (int maxLength : maxLengths)
        {
            formatBuilder.append("%-").append(maxLength + 2).append("s");
        }
        String format = formatBuilder.toString();

        StringBuilder result = new StringBuilder();
        for (List<String> row : rows)
        {
            result.append(String.format(format, row.toArray(new String[0]))).append("\n");
        }
        return result.toString();
    }

}
