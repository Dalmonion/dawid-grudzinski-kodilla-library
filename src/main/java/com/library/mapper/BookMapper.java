package com.library.mapper;

import com.library.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {
    public Book mapToBookForFirstSave(final BookDto bookDto) {
        return new Book(
                bookDto.getTitleId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getReleaseDate(),
                new ArrayList<>()
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getTitleId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseDate(),
                book.getBookRecords().stream()
                        .map(BookRecord::getRecordId)
                        .collect(Collectors.toList())
        );
    }

    public BookDtoShort mapToBookDtoShort(final Book book) {
        List<Long> recordsIdList = book.getBookRecords().stream()
                .map(BookRecord::getRecordId)
                .collect(Collectors.toList());

        return new BookDtoShort(
                book.getTitleId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseDate(),
                recordsIdList
        );
    }
}
