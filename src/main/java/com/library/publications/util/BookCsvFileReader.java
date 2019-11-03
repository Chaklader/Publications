package com.library.publications.util;


import com.library.publications.models.Author;
import com.library.publications.models.Book;
import com.library.publications.service.AuthorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookCsvFileReader extends CsvFileReader {

    private AuthorService authorService;

    public BookCsvFileReader(AuthorService service) {
        this.authorService = service;
    }

    public List<Book> readBooksCsvData(String fileName) throws IOException {

        List<Book> books = new ArrayList<>();

        try {
            List<List<String>> lines = readCsvFile(fileName);
            lines.remove(0);

            List<Author> authors = new ArrayList<>();

            for (List<String> line : lines) {

                String title = line.get(0);
                String isbn = line.get(1);

                String[] emails = line.get(2).split(",");


                for (String email : emails) {

                    Author author = authorService.findById(email).get();
                    authors.add(author);
                }

                String description = line.get(3);

                Book book = new Book(title, isbn, description);
                book.setAuthors(authors);
                books.add(book);
            }
        }

        //
        catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }
}