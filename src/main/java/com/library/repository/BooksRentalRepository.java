package com.library.repository;

import com.library.domain.BooksRental;
import org.springframework.data.repository.CrudRepository;

public interface BooksRentalRepository extends CrudRepository<BooksRental, Long> {
}
