package com.library.repository;

import com.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Override
    Book save(Book book);

    Optional<Book> findByTitle(String title);

    Optional<Book> findByTitleId(Long bookId);
}
