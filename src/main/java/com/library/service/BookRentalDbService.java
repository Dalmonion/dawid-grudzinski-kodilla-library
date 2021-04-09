package com.library.service;

import com.library.controller.BookRecordController;
import com.library.domain.*;
import com.library.mapper.BookRecordMapper;
import com.library.mapper.BookRentalMapper;
import com.library.repository.BooksRentalRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookRentalDbService {
    private final BooksRentalRepository booksRentalRepository;
    private final BookRentalMapper bookRentalMapper;
    private final BookRecordController bookRecordController;

    @Autowired
    public BookRentalDbService(BooksRentalRepository booksRentalRepository, BookRentalMapper bookRentalMapper,
                               @Lazy BookRecordController bookRecordController) {
        this.booksRentalRepository = booksRentalRepository;
        this.bookRentalMapper = bookRentalMapper;
        this.bookRecordController = bookRecordController;
    }

    //    public BooksRental saveRental(final BooksRental bookRental) {
//        return booksRentalRepository.save(bookRental);
//    }

    public BooksRental saveRental(final BooksRental bookRental) {
        return booksRentalRepository.save(bookRental);
    }

//    public Optional<BooksRental> getRentalRecord(Long id) {
//        return booksRentalRepository.findById(id);
//    }

    public BooksRentalDto getRentalRecord(Long id) throws BookRentalRecordNotFoundException {
        Optional<BooksRental> rentalRecord = booksRentalRepository.findById(id);
        return bookRentalMapper.mapToBooksRentalDto(rentalRecord.orElseThrow(BookRentalRecordNotFoundException::new));
    }

//    public void deleteRentalRecord(Long id) {
//        booksRentalRepository.deleteById(id);
//    }

    public void deleteRentalRecord(Long id) throws BookRentalRecordNotFoundException, BookRecordNotFoundException {
        BooksRentalDto booksRentalDto = getRentalRecord(id);
        bookRecordController.updateRecordStatus(booksRentalDto.getRecord().getRecordId(), Status.AVAILABLE);
        booksRentalRepository.deleteById(id);
    }
}
