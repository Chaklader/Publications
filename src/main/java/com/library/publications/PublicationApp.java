package com.library.publications;


import com.library.publications.models.Author;
import com.library.publications.models.Book;
import com.library.publications.models.Magazine;
import com.library.publications.models.Publication;
import com.library.publications.service.AuthorService;
import com.library.publications.service.PublicationService;
import com.library.publications.util.AuthorCsvFileReader;
import com.library.publications.util.BookCsvFileReader;
import com.library.publications.util.MagazineCsvFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.List;


@EnableJpaRepositories("com.library.publications.repository")
@SpringBootApplication(scanBasePackages = {"com.library.publications"}, exclude = JpaRepositoriesAutoConfiguration.class)
public class PublicationApp implements CommandLineRunner {


    @Autowired
    private PublicationService pubService;

    @Autowired
    private AuthorService authorService;

    public void saveCsvDataIntoDatabase() throws IOException {

        /*
         * save all the authors to the db
         * */
        String fileName = "/Users/Chaklader/IdeaProjects/Publications/src/main/resources/data/authors.csv";
        List<Author> authors = AuthorCsvFileReader.readAuthorsCsvData(fileName);
        authorService.saveAll(authors);

        /*
         * save all the books to the db
         * */
        fileName = "/Users/Chaklader/IdeaProjects/Publications/src/main/resources/data/books.csv";
        List<Book> books = BookCsvFileReader.readBooksCsvData(fileName);
        pubService.getBookService().saveAll(books);

        /*
         * save all the magazines to the db
         * */
        fileName = "/Users/Chaklader/IdeaProjects/Publications/src/main/resources/data/magazines.csv";
        List<Magazine> magazines = MagazineCsvFileReader.readMagazinesCsvData(fileName);
        pubService.getMagazineService().saveAll(magazines);

        System.out.println("ALL data saved to the database");
    }


    @Override
    public void run(String... strings) throws Exception {

        System.out.println("Hello Miami");

        /*
         * task 1: Your software should read all data from
         * the given CSV files in a meaningful structure.
         * */
        saveCsvDataIntoDatabase();

        /*
         * task 2: Print out all books and magazines (could be a GUI, console, …)
         * with all their details (with a meaningful output format). **Hint**: Do
         * not call `printBooks(...)` first and then `printMagazines(...)` ;-)
         * */
        List<Publication> list = pubService.getAllPublications();
        System.out.println("Size = " +list.size());
    }

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(PublicationApp.class);
        application.run(args);
    }
}