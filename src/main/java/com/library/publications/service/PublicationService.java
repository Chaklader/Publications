package com.library.publications.service;

import com.library.publications.models.Publication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PublicationService {

    private BookService bookService;

    private MagazineService magazineService;

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

    public List<Publication> getAllPublicationsByBookAuthorsEmail(List<String> emails) {

        publications.addAll(bookService.findAll());
        publications.addAll(magazineService.findAll());

        return publications;
    }
}
