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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chaklader on 2019-11-03
 */
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


    /*
     * print all the publications with details
     * */
    public void printAllPublications(List<Publication> pubs) {

        List<Book> books = new ArrayList<>();
        List<Magazine> magazines = new ArrayList<>();

        for (Publication p : pubs) {

            if (p instanceof Book) {
                books.add((Book) p);
            }

            //
            else if (p instanceof Magazine) {
                magazines.add((Magazine) p);
            }
        }

        System.out.println("\n\nALL the Books are printed:\n\n");

        for (Book book : books) {
            System.out.println(book.toString());
        }

        System.out.println("\n\nALL the Magazines are printed:\n\n");

        for (Magazine magazine : magazines) {
            System.out.println(magazine.toString());
        }

        System.out.println("\n\n");
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
        printAllPublications(list);


        /*
         * task 3: Find a book or magazine by its `isbn`
         * */
        String bookISBN = "5554-5545-4518";
        String magazineISBN = "5454-5587-3210";

        Book book = pubService.getBookService().findBookByIsbn(bookISBN).get();
        System.out.println("\n\nBook found by the ISBN\n\n");
        System.out.println(book);


        Magazine magazine = pubService.getMagazineService().findMagazineByIsbn(magazineISBN).get();
        System.out.println("\n\nMagazine found by the ISBN");
        System.out.println(magazine);



        /*
         * task 4: Find all books and magazines by their `bookAuthors`’ email.
         * */
//        List<Publication> result = pubService.getAllPublicationsByBookAuthorsEmail(new Publication());
    }

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(PublicationApp.class);
        application.run(args);
    }
}