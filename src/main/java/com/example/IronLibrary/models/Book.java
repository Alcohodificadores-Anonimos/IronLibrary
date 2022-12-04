package com.example.IronLibrary.models;

import jakarta.persistence.*;
import java.util.Formatter;
/*
This class will have:

Variable called isbn of data type string, representing the International Standard Book Number and acting as the unique identifier for the table book (Private member)
Variable called title of data type string (Private member)
Variable called category of data type string (Private member)
Variable called quantity of data type integer (Private member)
A parameterized constructor that takes isbn, title, category and a quantity
Public Getter functions to access these variables
Public Setter functions to change these variables
Optional attributes are accepted if needed based on the code structure
 */

@Entity
public class Book {

    @Id
    private String isbn;
    private String title;
    private String category;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;
    @OneToOne(mappedBy = "issueBook")
    private Issue issueBookId;

    public Book() {
    }

    public Book(String isbn, String title, String category, Integer quantity, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.quantity = quantity;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Issue getIssueBookId() {
        return issueBookId;
    }

    public void setIssueBookId(Issue issueBookId) {
        this.issueBookId = issueBookId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", author=" + author +
                ", issueBookId=" + issueBookId +
                '}';
    }

    public String toStringSimplifiedd() {
        return isbn + "\t"+"\t" + title + "\t"+"\t" +category + "\t"+"\t" + quantity;
    }

    public String toStringWithAuthor() {
        return isbn + "\t"+"\t" + title + "\t"+"\t" +category + "\t"+"\t" + quantity + "\t"+"\t" + author.getName()+ "\t"+"\t"+"\t" + author.getEmail();
    }

    public Formatter toStringSimplified(Formatter fmt) {
        return fmt.format("%15s %14s %14s %15s\n", isbn,title,category,quantity);
    }

    public Formatter toStringListWithAuthor(Formatter fmt) {
        return fmt.format("%15s %14s %14s %15s %15s %15s\n", isbn,title,category,quantity,author.getName(),author.getEmail());
    }

}
