package com.library.publications.service;

import com.library.publications.models.Author;
import com.library.publications.models.Book;
import com.library.publications.models.Magazine;
import com.library.publications.models.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Chaklader on 2019-11-03
 */
@Service
public class PublicationService {

    @Autowired
    private BookService bookService;

    @Autowired
    private MagazineService magazineService;

    @Autowired
    private AuthorService authorService;

    Set<Publication> publications;

    public PublicationService(BookService bookService, MagazineService magazineService) {
        this.bookService = bookService;
        this.magazineService = magazineService;

        publications = new HashSet<>();
    }

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public MagazineService getMagazineService() {
        return magazineService;
    }

    public void setMagazineService(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    public Set<Publication> getAllPublications() {

        publications.addAll(bookService.findAll());
        publications.addAll(magazineService.findAll());

        return publications;
    }

    /*
     * find all the books and magazines by a
     * publications book authors email addresses
     * */
    public Set<Publication> getAllPublicationsByBookAuthorsEmail(Publication pub) {

        List<String> emails = null;

        if (pub instanceof Book) {
            emails = ((Book) pub).getBookAuthors();

        } else if (pub instanceof Magazine) {
            emails = ((Magazine) pub).getBookAuthors();
        }

        Set<Book> books = new HashSet<>();
        Set<Magazine> magazines = new HashSet<>();

        System.out.println("\n\nAll the Book Authors Emails: "+ emails.toString()+"\n\n");

        for (String email : emails) {

            Author author = authorService.findById(email).get();

            books.addAll(author.getBooks());
            magazines.addAll(author.getMagazines());
        }

        Set<Publication> result = new HashSet<>();

        result.addAll(books);
        result.addAll(magazines);

        return result;
    }
}
