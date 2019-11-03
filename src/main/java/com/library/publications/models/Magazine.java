package com.library.publications.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "Magazine")
public class Magazine extends Publication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String isbn;

    @Column
    private String publishedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "magazine_authors",
            joinColumns = @JoinColumn(name = "magazine_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    List<Author> authors;

    public Magazine() {

    }

    public Magazine(String title, String isbn, String publishedAt) {

        this.title = title;
        this.isbn = isbn;
        this.publishedAt = publishedAt;
    }

    public Magazine(String title, String isbn, String publishedAt, List<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.publishedAt = publishedAt;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getBookAuthors(){

        List<String> list = new ArrayList<>();

        for(Author author : getAuthors()){
            list.add(author.getEmail());
        }

        return list;
    }

    public List<String> getBookAuthorsEmail(){

        List<String> emails = new ArrayList<>();

        for(Author author : getAuthors()){
            emails.add(author.getEmail());
        }

        return emails;
    }

    public void setBookAuthors(List<Author> bookAuthors) {
        this.authors = bookAuthors;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (Author author: authors){
            builder.append(author.getFirstname()+" "+author.getLastname()+" " + author.getEmail() + ",");
        }

        String authors = builder.toString();
        authors = authors.substring(0, authors.length()-1);

        String result = new StringBuilder().append(title +"\t"+ isbn +"\t" + authors+ "\t"+ publishedAt).toString();
        return result;
    }
}

