package com.library.repository;

import com.library.domain.BooksRental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRentalRepository extends CrudRepository<BooksRental, Long> {

    @Override
    BooksRental save(BooksRental booksRental);
}
