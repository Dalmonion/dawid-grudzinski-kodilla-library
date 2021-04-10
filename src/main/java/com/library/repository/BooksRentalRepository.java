package com.library.repository;

import com.library.domain.BooksRental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRentalRepository extends CrudRepository<BooksRental, Long> {

    @Override
    BooksRental save(BooksRental booksRental);

    @Override
    Optional<BooksRental> findById(Long id);
}
