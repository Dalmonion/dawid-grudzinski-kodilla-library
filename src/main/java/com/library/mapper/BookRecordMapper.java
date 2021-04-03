package com.library.mapper;

import com.library.domain.Book;
import com.library.domain.BookRecord;
import com.library.domain.BookRecordDto;
import com.library.domain.BookRecordDtoShort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRecordMapper {
    public BookRecord mapToBookRecord(final BookRecordDto bookRecordDto) {
        return new BookRecord(
                bookRecordDto.getRecordId(),
                bookRecordDto.getStatus(),
                bookRecordDto.getBook()
        );
    }

    public BookRecordDto mapToBookRecordDto(final BookRecord bookRecord) {
        return new BookRecordDto(
                bookRecord.getRecordId(),
                bookRecord.getStatus(),
                bookRecord.getBook()
        );
    }

    public List<BookRecordDto> mapToBookRecordDtoList(final List<BookRecord> bookRecordList) {
        return bookRecordList.stream()
                .map(this::mapToBookRecordDto)
                .collect(Collectors.toList());
    }

    public BookRecordDtoShort mapToBookRecordDtoShort(final BookRecord bookRecord) {
        return new BookRecordDtoShort(
                bookRecord.getRecordId(),
                bookRecord.getStatus(),
                bookRecord.getBook().getTitleId()
        );
    }

    public List<BookRecordDtoShort> mapToBookRecordDtoListShort(final List<BookRecord> bookRecordList) {
        return bookRecordList.stream()
                .map(this::mapToBookRecordDtoShort)
                .collect(Collectors.toList());
    }

}
