package com.library.publications.repository;

import com.library.publications.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

/**
 * @author Chaklader on 2019-11-03
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book WHERE book.isbn=:isbn",nativeQuery = true)
    Optional<Book> findBookByIsbn(String isbn);
}
