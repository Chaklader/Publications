package com.library.publications.repository;

import com.library.publications.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chaklader on 2019-11-03
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

//    @Query(value = "SELECT * FROM author WHERE author.email=:email",nativeQuery = true)
//    Optional<Author> findAuthorByEmail(String email);
}
