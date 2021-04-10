package com.library.service;

import com.library.domain.*;
import com.library.mapper.BookRentalMapper;
import com.library.repository.BooksRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class BookRentalDbService {
    private final BooksRentalRepository booksRentalRepository;
    private final BookRentalMapper bookRentalMapper;
    private final UserDbService userDbService;
    private final BookRecordDbService bookRecordDbService;

    @Autowired
    public BookRentalDbService(BooksRentalRepository booksRentalRepository, BookRentalMapper bookRentalMapper,
                               @Lazy UserDbService userDbService, @Lazy BookRecordDbService bookRecordDbService) {
        this.booksRentalRepository = booksRentalRepository;
        this.bookRentalMapper = bookRentalMapper;
        this.userDbService = userDbService;
        this.bookRecordDbService = bookRecordDbService;
    }

    public BooksRental saveRental(final BooksRental bookRental) {
        return booksRentalRepository.save(bookRental);
    }

    public BooksRentalDto getRentalRecord(Long id) throws BookRentalRecordNotFoundException, UserNotFoundException,
            BookNotFoundException, BookRecordNotFoundException {

        BooksRental rentalRecord = booksRentalRepository.findById(id).orElseThrow(BookRentalRecordNotFoundException::new);
        UserDto userDto = userDbService.getUser(rentalRecord.getUserId().getUserId());
        BookRecordDto bookRecordDto = bookRecordDbService.getRecord(rentalRecord.getRecordId().getRecordId());
        return bookRentalMapper.mapToBooksRentalDto(rentalRecord, userDto, bookRecordDto);
    }

    public void deleteRentalRecord(Long id) throws BookRentalRecordNotFoundException, BookRecordNotFoundException,
            BookNotFoundException, UserNotFoundException {

        BooksRentalDto booksRentalDto = getRentalRecord(id);
        bookRecordDbService.updateRecord(booksRentalDto.getRecordDto().getRecordId(), Status.AVAILABLE);
        booksRentalRepository.deleteById(id);
    }
}
