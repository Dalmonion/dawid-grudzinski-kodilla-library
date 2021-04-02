package com.library.repository;

import com.library.domain.BookRecord;
import org.springframework.data.repository.CrudRepository;

public interface BookRecordRepository extends CrudRepository<BookRecord, Long> {
}
