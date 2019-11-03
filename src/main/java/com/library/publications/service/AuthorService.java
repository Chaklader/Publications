package com.library.publications.service;

import com.library.publications.models.Author;
import com.library.publications.repository.AuthorRepository;
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
public class AuthorService {


    private AuthorRepository repository;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.repository = authorRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Optional<Author> findById(String email) {
        return repository.findById(email);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Author> findAll() {
        return (List<Author>) repository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Author save(Author Product) {
        return repository.save(Product);
    }

    @Transactional
    public <S extends Author> List<Author> saveAll(List<Author> students) {

        List<Author> result = new ArrayList<>();

        if (students == null) {
            return result;
        }

        for (Author student : students) {
            result.add(repository.save(student));
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAll() {
        repository.deleteAll();
    }
}
