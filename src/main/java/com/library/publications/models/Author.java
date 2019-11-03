package com.library.publications.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name = "Author")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "email")
    private String email;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    List<Book> books;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    List<Magazine> magazines;

    public Author() {

    }

    public Author(String email, String fName, String lName) {
        this.email = email;
        this.firstname = fName;
        this.lastname = lName;
    }


    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;
        return
                Objects.equals(getFirstname(), author.getFirstname()) &&
                Objects.equals(getLastname(), author.getLastname()) &&
                Objects.equals(getBooks(), author.getBooks()) &&
                Objects.equals(getMagazines(), author.getMagazines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getLastname());
    }

    @Override
    public String toString() {
        return "Author{" +
                "email=" + email +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
