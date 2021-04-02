package com.library.repository;

import com.library.domain.BookRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRecordRepository extends CrudRepository<BookRecord, Long> {
}
