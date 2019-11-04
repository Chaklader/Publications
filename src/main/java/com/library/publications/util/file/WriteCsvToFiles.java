package com.library.publications.util.file;

import com.library.publications.models.Author;
import com.library.publications.models.Book;
import com.library.publications.models.Magazine;

import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import static com.library.publications.util.Parameters.newBookFile;
import static com.library.publications.util.Parameters.newMagazineFile;

/**
 * @author Chaklader on 2019-11-04
 */
public class WriteCsvToFiles {


    public static void writeBooksDataToCsvFile(List<Book> books) {

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

    public static void writeMagazinesDataToCsvFile(List<Magazine> magazines) {


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
}
