package com.library.mapper;

import com.library.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookRecordMapper {
    public BookRecord mapToBookRecord(final BookRecordDto bookRecordDto, final Book book) {
        return new BookRecord(
                bookRecordDto.getRecordId(),
                bookRecordDto.getStatus(),
                book
        );
    }

    public BookRecordDto mapToBookRecordDto(final BookRecord bookRecord, BookDto bookDto) {
        return new BookRecordDto(
                bookRecord.getRecordId(),
                bookRecord.getStatus(),
                bookDto
        );
    }

    public List<BookRecordDto> mapToBookRecordDtoList(final List<BookRecord> bookRecordList, BookDto bookDto) {
        List<BookRecordDto> resultList = new ArrayList<>();
        bookRecordList.forEach(bookRecord -> resultList.add(mapToBookRecordDto(bookRecord, bookDto)));

        return resultList;
    }
}
