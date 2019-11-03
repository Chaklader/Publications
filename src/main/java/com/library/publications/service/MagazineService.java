package com.library.publications.service;

import com.library.publications.models.Magazine;
import com.library.publications.repository.MagazineRepository;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class MagazineService {


    private MagazineRepository repository;

    @Autowired
    public void setMagazineRepository(MagazineRepository MagazineRepository) {
        this.repository = MagazineRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Optional<Magazine> findById(Long id) {
        return Optional.of(repository.findOne(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Magazine> findAll() {
        return (List<Magazine>) repository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Magazine save(Magazine Product) {
        return repository.save(Product);
    }

    @Transactional
    public <S extends Magazine> List<Magazine> saveAll(List<Magazine> students) {

        List<Magazine> result = new ArrayList<>();

        if (students == null) {
            return result;
        }

        for (Magazine student : students) {
            result.add(repository.save(student));
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Optional<Magazine> findMagazineByIsbn(String isbn){
        return repository.findMagazineByIsbn(isbn);
    }
}
