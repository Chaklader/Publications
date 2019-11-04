package com.library.publications.util.database;

import com.library.publications.models.Author;
import com.library.publications.models.Book;
import com.library.publications.models.Magazine;
import com.library.publications.service.AuthorService;
import com.library.publications.util.file.BookCsvFileReader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Chaklader on 2019-11-04
 */
@Component
public class CreateEntities {


    /*
     * create a book from the String info provided
     * */
    public static Book createdBook(AuthorService authorService, String s) {

        List<String> line = BookCsvFileReader.parseLine(s);
        List<Author> authors = new ArrayList<>();

        String title = line.get(0);
        String isbn = line.get(1);

        String[] emails = line.get(2).split(",");

        for (String email : emails) {

            Author author = new Author(email);
            authorService.save(author);

            authors.add(author);
        }

        String description = line.get(3);

        Book book = new Book(title, isbn, description);
        book.setAuthors(authors);

        return book;
    }


    /*
     * create a magazine from the String info provided
     * */
    public static Magazine createMagazine(AuthorService authorService, String s) {

        List<String> line = BookCsvFileReader.parseLine(s);
        List<Author> authors = new ArrayList<>();

        String title = line.get(0);
        String isbn = line.get(1);

        String[] emails = line.get(2).split(",");

        for (String email : emails) {

            Author author = new Author(email);
            authorService.save(author);

            authors.add(author);
        }

        String publishedAt = line.get(3);

        Magazine magazine = new Magazine(title, isbn, publishedAt);
        magazine.setAuthors(authors);

        return magazine;
    }

}
