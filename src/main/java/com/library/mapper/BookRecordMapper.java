package com.library.mapper;

import com.library.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRecordMapper {

    private final BookMapper bookMapper = new BookMapper();

//    @Autowired
//    public void setBookMapper(BookMapper bookMapper) {
//        this.bookMapper = bookMapper;
//    }

    public BookRecord mapToBookRecord(final BookRecordDto bookRecordDto) {
        Book book = bookMapper.mapToBook(bookRecordDto.getBook());
        return new BookRecord(
                bookRecordDto.getRecordId(),
                bookRecordDto.getStatus(),
                book
        );
    }

    public BookRecordDto mapToBookRecordDto(final BookRecord bookRecord) {
        BookDto bookDto = bookMapper.mapToBookDto(bookRecord.getBook());
        return new BookRecordDto(
                bookRecord.getRecordId(),
                bookRecord.getStatus(),
                bookDto
        );
    }

    public List<BookRecordDto> mapToBookRecordDtoList(final List<BookRecord> bookRecordList) {
        if (bookRecordList == null) {
            return new ArrayList<>();
        } else {
            return bookRecordList.stream()
                    .map(this::mapToBookRecordDto)
                    .collect(Collectors.toList());
        }
    }

    public List<BookRecord> mapToBookRecordList(final List<BookRecordDto> bookRecordDtoList) {
        if (bookRecordDtoList == null) {
            return new ArrayList<>();
        } else {
            return bookRecordDtoList.stream()
                    .map(this::mapToBookRecord)
                    .collect(Collectors.toList());
        }

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
