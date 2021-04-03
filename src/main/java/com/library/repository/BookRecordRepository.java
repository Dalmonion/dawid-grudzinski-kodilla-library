package com.library.repository;

import com.library.domain.Book;
import com.library.domain.BookRecord;
import com.library.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRecordRepository extends CrudRepository<BookRecord, Long> {

    @Override
    BookRecord save(BookRecord bookRecord);

    @Override
    List<BookRecord> findAll();

    @Override
    Optional<BookRecord> findById(Long id);

    @Query
    List<BookRecord> retrieveAvailableRecords();
}
