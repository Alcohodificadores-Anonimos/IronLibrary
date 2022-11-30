package com.example.IronLibrary.models;

import jakarta.persistence.*;

import java.time.LocalDate;

/*

This class will have:

Variable called issueId of data type integer, auto-incremented (Private member)
Variable called issueDate of data type string (Private member)
Variable called returnDate of data type string (Private member)
Variable called issueStudent of data type Student, representing a One-to-One relationship with Student(Private member)
Variable called issueBook of data type Book, representing a One-to-One relationship with Book (Private member)
A parameterized constructor that takes issueDate, returnDate, issueStudent and issueBook
Public Getter functions to access these variables
Public Setter functions to change these variables
Optional attributes are accepted if needed based on the code structure
 */

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer issueId;
    private LocalDate issueDate;
    private LocalDate returnDate;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student issueStudent;
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book issueBook;


    public Issue() {
    }

    public Issue(LocalDate issueDate, LocalDate returnDate, Student issueStudent, Book issueBook) {
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.issueStudent = issueStudent;
        this.issueBook = issueBook;
    }

}
