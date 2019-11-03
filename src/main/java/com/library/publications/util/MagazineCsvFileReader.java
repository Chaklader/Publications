package com.library.publications.util;

import com.library.publications.models.Author;
import com.library.publications.models.Magazine;
import com.library.publications.service.AuthorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MagazineCsvFileReader extends CsvFileReader {


    private AuthorService authorService;

    public MagazineCsvFileReader(AuthorService authorService) {
        this.authorService = authorService;
    }

    public List<Magazine> readMagazinesCsvData(String fileName) throws IOException {

        List<Magazine> magazines = new ArrayList<>();

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

                String publishedAt = line.get(3);

                Magazine magazine = new Magazine(title, isbn, publishedAt);

                magazine.setAuthors(authors);
                magazine.setBookAuthors(authors);

                magazines.add(magazine);
            }
        }

        //
        catch (IOException e) {
            e.printStackTrace();
        }

        return magazines;
    }
}