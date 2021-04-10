package com.library.service;

import com.library.domain.*;
import com.library.mapper.BookRecordMapper;
import com.library.mapper.BookRentalMapper;
import com.library.mapper.UserMapper;
import com.library.repository.BooksRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookRentalDbService {
    private final BooksRentalRepository booksRentalRepository;
    private final BookRentalMapper bookRentalMapper;
    private final UserDbService userDbService;
    private final BookRecordDbService bookRecordDbService;
    private final BookDbService bookDbService;
    private final UserMapper userMapper;
    private final BookRecordMapper bookRecordMapper;

    @Autowired
    public BookRentalDbService(BooksRentalRepository booksRentalRepository, BookRentalMapper bookRentalMapper,
                               UserMapper userMapper, BookRecordMapper bookRecordMapper,
                               @Lazy UserDbService userDbService, @Lazy BookRecordDbService bookRecordDbService,
                               @Lazy BookDbService bookDbService) {
        this.booksRentalRepository = booksRentalRepository;
        this.bookRentalMapper = bookRentalMapper;
        this.userDbService = userDbService;
        this.bookRecordDbService = bookRecordDbService;
        this.bookDbService = bookDbService;
        this.userMapper = userMapper;
        this.bookRecordMapper = bookRecordMapper;
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

    public void rent(Long userId, String bookTitle, LocalDate rentUntil) throws BookRecordNotFoundException,
            UserNotFoundException, BookNotFoundException {

        List<BookRecordDto> records = bookRecordDbService.getAvailableRecordsByBookId(bookTitle);
        if (!records.isEmpty()) {
            Long recordId = records.get(0).getRecordId();
            BookRecordDto recordDto = bookRecordDbService.getRecord(recordId);
            UserDto userDto = userDbService.getUser(userId);
            Book book = bookDbService.findBookByTitleLong(bookTitle);

            BooksRental booksRental = new BooksRental(userMapper.mapToUser(userDto),
                    bookRecordMapper.mapToBookRecord(recordDto, book), LocalDate.now(), rentUntil);
            saveRental(booksRental);
            bookRecordDbService.updateRecord(recordId, Status.RENTED);
        }
    }
}
