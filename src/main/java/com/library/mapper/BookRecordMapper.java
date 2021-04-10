package com.library.mapper;

import com.library.controller.BookController;
import com.library.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRecordMapper {

//    private BookMapper bookMapper;
//    private BookController bookController;

//    @Autowired
//    public void setBookController(BookController bookController) {
//        this.bookController = bookController;
//    }
//
//    @Autowired
//    public void setBookMapper(BookMapper bookMapper) {
//        this.bookMapper = bookMapper;
//    }

    public BookRecord mapToBookRecord(final BookRecordDto bookRecordDto, final Book book) {
        return new BookRecord(
                bookRecordDto.getRecordId(),
                bookRecordDto.getStatus(),
                book
        );
    }

//    public BookRecord mapToBookRecordShort(final BookRecordDtoShort bookRecordDtoShort) {
//        Book book = bookMapper.mapToBook(bookRecordDtoShort.getBook());
//        return new BookRecord(
//                bookRecordDtoShort.getRecordId(),
//                bookRecordDtoShort.getStatus(),
//                book
//        );
//    }

    public BookRecordDto mapToBookRecordDto(final BookRecord bookRecord, BookDto bookDto) {
        return new BookRecordDto(
                bookRecord.getRecordId(),
                bookRecord.getStatus(),
                bookDto
        );
    }

//    public BookRecordDtoShort mapToBookRecordDtoShort(final BookRecord bookRecord) {
//        return new BookRecordDtoShort(
//                bookRecord.getRecordId(),
//                bookRecord.getStatus(),
//                bookRecord.getBook().getTitleId()
//        );
//    }

    public List<BookRecordDto> mapToBookRecordDtoList(final List<BookRecord> bookRecordList, BookDto bookDto) {
        List<BookRecordDto> resultList = new ArrayList<>();
        bookRecordList.forEach(bookRecord -> {
            resultList.add(mapToBookRecordDto(bookRecord, bookDto));
        });

        return resultList;
    }
//
//    public List<BookRecord> mapToBookRecordList(final List<BookRecordDto> bookRecordDtoList) {
//            return bookRecordDtoList.stream()
//                    .map(this::mapToBookRecord)
//                    .collect(Collectors.toList());
//    }

//    public List<BookRecordDtoShort> mapToBookRecordDtoListShort(final List<BookRecord> bookRecordList) {
//        return bookRecordList.stream()
//                .map(this::mapToBookRecordDtoShort)
//                .collect(Collectors.toList());
//    }

}
