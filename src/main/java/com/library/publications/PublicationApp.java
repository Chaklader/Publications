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
import com.library.publications.util.Parameters;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static com.library.publications.util.Parameters.*;

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
     * save all the data from the CSV files to the database
     * */
    public void saveCsvDataIntoDatabase() throws IOException {

        /*
         * save all the authors to the db
         * */
        String fileName = authorFile;
        List<Author> authors = AuthorCsvFileReader.readAuthorsCsvData(fileName);
        authorService.saveAll(authors);

        /*
         * save all the books to the db
         * */
        fileName = bookFile;
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


    /*
     * print all the publications with details
     * */
    public void printPublications(Set<Publication> pubs) {

        for (Publication p : pubs) {
            System.out.println(p.toString());
        }
    }

    public Book createdBook(String s) {

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


    private Magazine createMagazine(String s) {

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

    private void writeBooksDataToCsvFile(List<Book> books) {

        String filename = newBookFile;

        try {

            FileWriter fw = new FileWriter(filename);
            Iterator<Book> iterator = books.iterator();

            fw.append("title;isbn;authors;description\n");

            while (iterator.hasNext()) {

                Book book = iterator.next();
                StringBuilder builder = new StringBuilder();

                builder.append(book.getTitle()).append(";");
                builder.append(book.getIsbn()).append(";");

                for (Author author : book.getAuthors()) {
                    builder.append(author.getEmail()).append(",");
                }

                String s = builder.toString();
                s = s.substring(0, s.length() - 1);

                builder = new StringBuilder(s).append(";").append(book.getDescription());

                fw.append(builder.toString());
                fw.append('\n');
            }

            fw.flush();
            fw.close();
            System.out.println("New Csv Books File is created successfully.");
        }

        //
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void writeMagazinesDataToCsvFile(List<Magazine> magazines) {


        String filename = newMagazineFile;

        try {

            FileWriter fw = new FileWriter(filename);
            Iterator<Magazine> iterator = magazines.iterator();

            fw.append("title;isbn;authors;publishedAt\n");

            while (iterator.hasNext()) {

                Magazine book = iterator.next();
                StringBuilder builder = new StringBuilder();

                builder.append(book.getTitle()).append(";");
                builder.append(book.getIsbn()).append(";");

                for (Author author : book.getAuthors()) {
                    builder.append(author.getEmail()).append(",");
                }

                String s = builder.toString();
                s = s.substring(0, s.length() - 1);

                builder = new StringBuilder(s).append(";").append(book.getPublishedAt());

                fw.append(builder.toString());
                fw.append('\n');
            }

            fw.flush();
            fw.close();
            System.out.println("New Csv Magazines File is created successfully.");
        }

        //
        catch (Exception e) {
            e.printStackTrace();
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
        saveCsvDataIntoDatabase();

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
        Book newBook = createdBook(bookString);
        pubService.getBookService().save(newBook);


        List<Book> books = pubService.getBookService().findAll();
        writeBooksDataToCsvFile(books);


        String magazineString = "National Geographics Yearly;7754-5587-3210;maria.koval@echocat.com;21.05.2011";
        Magazine newMagazine = createMagazine(magazineString);
        pubService.getMagazineService().save(newMagazine);

        List<Magazine> magazines = pubService.getMagazineService().findAll();
        writeMagazinesDataToCsvFile(magazines);
    }
}