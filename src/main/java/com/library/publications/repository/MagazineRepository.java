package com.library.publications.repository;

import com.library.publications.models.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Chaklader on 2019-11-03
 */
@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {

    @Query(value = "SELECT * FROM magazine WHERE magazine.isbn=:isbn",nativeQuery = true)
    Optional<Magazine> findMagazineByIsbn(String isbn);
}
