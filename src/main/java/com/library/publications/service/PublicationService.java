package com.library.publications.service;

import com.library.publications.models.Author;
import com.library.publications.models.Book;
import com.library.publications.models.Magazine;
import com.library.publications.models.Publication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Chaklader on 2019-11-03
 */
@Component
public class PublicationService {

    private BookService bookService;

    private MagazineService magazineService;

    private AuthorService authorService;

    List<Publication> publications;

    public PublicationService(BookService bookService, MagazineService magazineService) {
        this.bookService = bookService;
        this.magazineService = magazineService;

        publications = new ArrayList<>();
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

    public List<Publication> getAllPublications() {

        publications.addAll(bookService.findAll());
        publications.addAll(magazineService.findAll());

        return publications;
    }

    /*
     * find all the books and magazines by a
     * publications book authors email addresses
     * */
    public List<Publication> getAllPublicationsByBookAuthorsEmail(Publication pub) {

        List<String> emails = null;

        if (pub instanceof Book) {
            emails = ((Book) pub).getBookAuthors();

        } else if (pub instanceof Magazine) {
            emails = ((Magazine) pub).getBookAuthors();
        }

        List<Author> authors = new ArrayList<>();

        Set<Book> books = new HashSet<>();
        Set<Magazine> magazines = new HashSet<>();


        for (String email : emails) {

            Author author = authorService.findById(email).get();

            books.addAll(author.getBooks());
            magazines.addAll(author.getMagazines());
        }

        List<Publication> result = new ArrayList<>();

        result.addAll(books);
        result.addAll(magazines);

        return result;
    }
}
