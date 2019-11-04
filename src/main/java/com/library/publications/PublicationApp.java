package com.library.publications;


import com.library.publications.models.Book;
import com.library.publications.models.Magazine;
import com.library.publications.models.Publication;
import com.library.publications.service.AuthorService;
import com.library.publications.service.PublicationService;

import com.library.publications.util.database.DatabaseStorage;
import com.library.publications.util.database.CreateEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.*;

import static com.library.publications.util.file.WriteCsvToFiles.writeBooksDataToCsvFile;
import static com.library.publications.util.file.WriteCsvToFiles.writeMagazinesDataToCsvFile;


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


    /*
     * print all the publications with details
     * */
    public void printPublications(Set<Publication> pubs) {

        for (Publication p : pubs) {
            System.out.println(p.toString());
        }
    }

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(PublicationApp.class);
        application.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {

        System.out.println("Hello Miami");

        /*
         * task 1: Your software should read all data from
         * the given CSV files in a meaningful structure.
         * */
        DatabaseStorage.saveAuthorsCsvDataIntoDatabase(authorService);
        DatabaseStorage.savePublicationsCsvDataIntoDatabase(pubService);

        /*
         * task 2: Print out all books and magazines (could be a GUI, console, …)
         * with all their details (with a meaningful output format). **Hint**: Do
         * not call `printBooks(...)` first and then `printMagazines(...)` ;-)
         * */
        Set<Publication> set = pubService.getAllPublications();
        printPublications(set);


        /*
         * task 3: Find a book or magazine by its `isbn`
         * */
        String bookISBN = "5554-5545-4518";
        String magazineISBN = "5454-5587-3210";

        Book book = pubService.getBookService().findBookByIsbn(bookISBN).get();
        System.out.println("\n\nBook found by the ISBN\n\n");
        System.out.println(book);

        Magazine magazine = pubService.getMagazineService().findMagazineByIsbn(magazineISBN).get();
        System.out.println("\n\nMagazine found by the ISBN\n");
        System.out.println(magazine);


        /*
         * task 4: Find all books and magazines by their `bookAuthors`’ email.
         * */
        System.out.println("\n\nFind all books and magazines by their `bookAuthors`’ email\n\n");

        Set<Publication> pubs = pubService.getAllPublicationsByBookAuthorsEmail(book);
        printPublications(pubs);

        System.out.println("\n\n");

        pubs = pubService.getAllPublicationsByBookAuthorsEmail(magazine);
        printPublications(pubs);

        System.out.println("\n\n");


        /*
         * task 5: Print out all books and magazines with all their details
         * sorted by `title`. This sort should be done for books and magazines
         * together.
         * */
        List<Publication> list = new ArrayList<>(pubService.getAllPublications());
        Collections.sort(list, Comparator.comparing(Publication::getTitle));

        System.out.println("\n\nPrint out all books and magazines with all their details sorted by the title\n\n");
        printPublications(new LinkedHashSet<>(list));


        /*
         * task 6: Write Unit tests for one or more methods
         * */


        /*
         * task 7: Implement an interactive user interface for one or more of
         * the main tasks mentioned above. This could be done by a website, on
         * the console, etc.
         * */


        /*
         * task 8: Add a book and a magazine to the data structure of your software
         * and export it to a new CSV files.
         * */
        String bookString = "Harry Potter and the Cursed Child;978-133-9133;J.K.Rowling@gmail.com,Jack.Thorne@gmail.com,John.Tiffany@gmail.com;It was always difficult being Harry Potter and it isn't much easier now.";
        Book newBook = CreateEntities.createdBook(authorService, bookString);
        pubService.getBookService().save(newBook);


        List<Book> books = pubService.getBookService().findAll();
        writeBooksDataToCsvFile(books);


        String magazineString = "National Geographics Yearly;7754-5587-3210;maria.koval@echocat.com;21.05.2011";
        Magazine newMagazine = CreateEntities.createMagazine(authorService, magazineString);
        pubService.getMagazineService().save(newMagazine);

        List<Magazine> magazines = pubService.getMagazineService().findAll();
        writeMagazinesDataToCsvFile(magazines);
    }
}