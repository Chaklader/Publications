package com.library.publications.service;


import com.library.publications.models.Book;
import com.library.publications.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Chaklader on 2019-11-03
 */
@Service
public class BookService {


    private BookRepository repository;

    @Autowired
    public void setBookRepository(BookRepository BookRepository) {
        this.repository = BookRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Optional<Book> findById(Long id) {
        return Optional.of(repository.findOne(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Book> findAll() {
        return (List<Book>) repository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Book save(Book Product) {
        return repository.save(Product);
    }

    @Transactional
    public <S extends Book> List<Book> saveAll(List<Book> students) {

        List<Book> result = new ArrayList<>();

        if (students == null) {
            return result;
        }

        for (Book student : students) {
            result.add(repository.save(student));
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Optional<Book> findBookByIsbn(String isbn){
        return repository.findBookByIsbn(isbn);
    }
}
