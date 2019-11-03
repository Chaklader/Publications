package com.library.publications.util;


import com.library.publications.models.Author;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorCsvFileReader extends CsvFileReader {


    public static List<Author> readAuthorsCsvData(String fileName) {

        List<Author> authors = null;

        try {

            authors = new ArrayList<>();

            List<List<String>> lines = readCsvFile(fileName);
            lines.remove(0);

            for (List<String> line : lines) {

                String email = line.get(0);
                String firstName = line.get(1);
                String lastName = line.get(2);

                Author author = new Author(email, firstName, lastName);
                authors.add(author);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return authors;
    }
}
