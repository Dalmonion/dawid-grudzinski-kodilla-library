package com.library.repository;

import com.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
