package com.library.publications.util.database;

import com.library.publications.models.Author;
import com.library.publications.models.Book;
import com.library.publications.models.Magazine;
import com.library.publications.service.AuthorService;
import com.library.publications.service.PublicationService;
import com.library.publications.util.file.AuthorCsvFileReader;
import com.library.publications.util.file.BookCsvFileReader;
import com.library.publications.util.file.MagazineCsvFileReader;

import java.io.IOException;
import java.util.List;

import static com.library.publications.util.Parameters.*;

/**
 * @author Chaklader on 2019-11-04
 */
public class DatabaseStorage {

    /*
     * save all the authors data from the CSV files to the database
     * */
    public static void saveAuthorsCsvDataIntoDatabase(AuthorService authorService) throws IOException {

        /*
         * save all the authors to the db
         * */
        String fileName = authorFile;
        List<Author> authors = AuthorCsvFileReader.readAuthorsCsvData(fileName);
        authorService.saveAll(authors);
    }


    /*
     * save all the publications data from the CSV files to the database
     * */
    public static void savePublicationsCsvDataIntoDatabase(PublicationService pubService) throws IOException {

        /*
         * save all the books to the db
         * */
        String fileName = bookFile;
        List<Book> books = BookCsvFileReader.readBooksCsvData(fileName);
        pubService.getBookService().saveAll(books);

        /*
         * save all the magazines to the db
         * */
        fileName = magazineFile;
        List<Magazine> magazines = MagazineCsvFileReader.readMagazinesCsvData(fileName);
        pubService.getMagazineService().saveAll(magazines);

        System.out.println("ALL data saved to the database");
    }

}
