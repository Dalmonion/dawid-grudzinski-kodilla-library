package com.library.repository;

import com.library.domain.BookRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRecordRepository extends CrudRepository<BookRecord, Long> {

    @Override
    BookRecord save(BookRecord bookRecord);

    @Override
    Optional<BookRecord> findById(Long id);

    @Query
    List<BookRecord> retrieveAvailableRecords();

    @Query(nativeQuery = true)
    List<BookRecord> getAvailableRecordsByBookId(@Param("ID") Long bookId);
}
