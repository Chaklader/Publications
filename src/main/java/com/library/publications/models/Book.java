package com.library.publications.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * @author Chaklader on 2019-11-03
 */

@Entity
@Table(name = "Book")
public class Book extends Publication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String isbn;

    @Column(length = 5000)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    List<Author> authors;

    public Book() {

    }

    public Book(String title, String isbn, String description) {
        super(title);
        this.isbn = isbn;
        this.description = description;
    }

    public Book(String title, String isbn, String description, List<Author> authors) {
        super(title);
        this.isbn = isbn;
        this.description = description;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getBookAuthors() {


        List<String> list = new ArrayList<>();

        for (Author author : getAuthors()) {
            list.add(author.getEmail());
        }

        return list;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setBookAuthors(List<Author> bookAuthors) {
        this.authors = bookAuthors;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (Author author : getAuthors()) {
            builder.append(author.getFirstname() + " " + author.getLastname() + " " + author.getEmail() + ",");
        }

        String authors = builder.toString();
        authors = authors.substring(0, authors.length() - 1);

        String result = new StringBuilder().append(getTitle() + "\t" + isbn + "\t" + authors + "\t" + description).toString();
        return result;
    }
}

