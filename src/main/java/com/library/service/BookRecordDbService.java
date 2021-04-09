package com.library.service;

import com.library.controller.BookController;
import com.library.controller.BookRecordController;
import com.library.controller.UserController;
import com.library.domain.*;
import com.library.mapper.BookMapper;
import com.library.mapper.BookRecordMapper;
import com.library.mapper.BookRentalMapper;
import com.library.mapper.UserMapper;
import com.library.repository.BookRecordRepository;
import com.library.repository.BooksRentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookRecordDbService {

    private final BookRecordRepository bookRecordRepository;
    private final BookMapper bookMapper;
    private final BookRecordMapper bookRecordMapper;
    private final BookController bookController;
    private final UserController userController;
    private final UserMapper userMapper;
    private final BookRentalMapper bookRentalMapper;
    private final BookRentalDbService bookRentalDbService;

    public BookRecordDbService(BookRecordRepository bookRecordRepository, BookRecordMapper bookRecordMapper,
                               BookController bookController, UserController userController, UserMapper userMapper,
                               BookRentalMapper bookRentalMapper, BookRentalDbService bookRentalDbService, BookMapper bookMapper) {
        this.bookRecordRepository = bookRecordRepository;
        this.bookRecordMapper = bookRecordMapper;
        this.bookController = bookController;
        this.userController = userController;
        this.userMapper = userMapper;
        this.bookRentalMapper = bookRentalMapper;
        this.bookRentalDbService = bookRentalDbService;
        this.bookMapper = bookMapper;
    }


    public BookRecord saveRecord(final BookRecordDto bookRecordDto) {
        BookRecord bookRecord = bookRecordMapper.mapToBookRecord(bookRecordDto);
        return bookRecordRepository.save(bookRecord);
    }

//    public BookRecord saveRecordShort(final BookRecordDtoShort bookRecordDtoShort) {
//        BookRecord bookRecord = bookRecordMapper.mapToBookRecordShort(bookRecordDtoShort);
//        return bookRecordRepository.save(bookRecord);
//    }

    public BookRecordDto updateRecord(Long recordId, Status status) throws BookRecordNotFoundException {
        BookRecordDto bookRecordDto = getRecord(recordId);
        BookRecord bookRecord = bookRecordMapper.mapToBookRecord(bookRecordDto);
        bookRecord.setStatus(status);
        BookRecord savedRecord = saveRecord(bookRecordMapper.mapToBookRecordDto(bookRecord));
        return bookRecordMapper.mapToBookRecordDto(savedRecord);
    }

    public BookRecordDto getRecord(Long id) throws BookRecordNotFoundException{
        BookRecord bookRecord = bookRecordRepository.findById(id).orElseThrow(BookRecordNotFoundException::new);
        return bookRecordMapper.mapToBookRecordDto(bookRecord);
    }

//    public List<BookRecord> getAvailableRecords() {
//        return bookRecordRepository.retrieveAvailableRecords();
//    }

    public List<BookRecordDto> getAvailableRecordsByBookId(String bookTitle) throws BookNotFoundException{
        BookDto bookDto = bookController.getBook(bookTitle);
        List<BookRecord> recordList = bookRecordRepository.getAvailableRecordsByBookId(bookDto.getTitleId());
        return bookRecordMapper.mapToBookRecordDtoList(recordList);
    }

    public void rent(Long userId, String bookTitle, LocalDate rentUntil) throws BookRecordNotFoundException,
            UserNotFoundException, BookNotFoundException {

        List<BookRecordDto> records = getAvailableRecordsByBookId(bookTitle);
        if (!records.isEmpty()) {
            Long recordId = records.get(0).getRecordId();
            BookRecordDto recordDto = getRecord(recordId);
            UserDto userDto = userController.getUser(userId);
            BooksRentalDto booksRentalDto = new BooksRentalDto(userMapper.mapToUser(userDto),
                    bookRecordMapper.mapToBookRecord(recordDto), LocalDate.now(), rentUntil);

            BooksRental booksRental = bookRentalMapper.mapToBooksRental(booksRentalDto);
            bookRentalDbService.saveRental(booksRental);
            updateRecord(recordId, Status.RENTED);
        }
    }
}
