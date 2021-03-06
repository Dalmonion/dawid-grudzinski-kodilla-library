package com.library.service;

import com.library.domain.*;
import com.library.mapper.BookRecordMapper;
import com.library.mapper.UserMapper;
import com.library.repository.BookRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookRecordDbService {

    private final BookRecordRepository bookRecordRepository;
    private final BookRecordMapper bookRecordMapper;
    private final BookDbService bookDbService;

    public BookRecordDbService(BookRecordRepository bookRecordRepository, BookRecordMapper bookRecordMapper,
                               BookDbService bookDbService) {
        this.bookRecordRepository = bookRecordRepository;
        this.bookRecordMapper = bookRecordMapper;
        this.bookDbService = bookDbService;
    }

    public BookRecord saveRecord(BookRecordDto bookRecordDto) throws BookNotFoundException {
        Book book = bookDbService.findBookByIdLong(bookRecordDto.getBook().getTitleId());
        BookRecord bookRecord = bookRecordMapper.mapToBookRecord(bookRecordDto, book);
        return bookRecordRepository.save(bookRecord);
    }

    public BookRecordDto updateRecord(Long recordId, Status status) throws BookRecordNotFoundException, BookNotFoundException {
        BookRecord bookRecord = getRecordLong(recordId);
        bookRecord.setStatus(status);
        BookDto bookDto = bookDbService.findBookById(bookRecord.getBook().getTitleId());
        BookRecordDto bookRecordDto = bookRecordMapper.mapToBookRecordDto(bookRecord, bookDto);
        BookRecord savedBookRecord = saveRecord(bookRecordDto);
        BookDto savedBookDto = bookDbService.findBookById(savedBookRecord.getBook().getTitleId());
        return bookRecordMapper.mapToBookRecordDto(savedBookRecord, savedBookDto);

    }

    public BookRecordDto getRecord(Long id) throws BookNotFoundException, BookRecordNotFoundException {
        BookRecord bookRecord = bookRecordRepository.findById(id).orElseThrow(BookRecordNotFoundException::new);
        BookDto bookDto = bookDbService.findBookById(bookRecord.getBook().getTitleId());
        return bookRecordMapper.mapToBookRecordDto(bookRecord, bookDto);
    }

    private BookRecord getRecordLong(Long id) throws BookRecordNotFoundException {
        return bookRecordRepository.findById(id).orElseThrow(BookRecordNotFoundException::new);
    }

    public List<BookRecordDto> getAvailableRecordsByBookId(String bookTitle) throws BookNotFoundException, BookRecordNotFoundException {
        BookDto bookDto = bookDbService.findBookByTitle(bookTitle);
        List<BookRecord> recordList = bookRecordRepository.getAvailableRecordsByBookId(bookDto.getTitleId());
        if (recordList.isEmpty()) {
            throw new BookRecordNotFoundException();
        } else {
            return bookRecordMapper.mapToBookRecordDtoList(recordList, bookDto);
        }
    }
}
