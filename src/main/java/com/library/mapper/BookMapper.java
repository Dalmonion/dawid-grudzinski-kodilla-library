package com.library.mapper;

import com.library.domain.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {


    private BookRecordMapper bookRecordMapper;

    @Autowired
    public void setBookRecordMapper(BookRecordMapper bookRecordMapper) {
        this.bookRecordMapper = bookRecordMapper;
    }

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getTitleId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getReleaseDate(),
                bookRecordMapper.mapToBookRecordList(bookDto.getBookRecords())
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getTitleId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseDate(),
                bookRecordMapper.mapToBookRecordDtoList(book.getBookRecords())
        );
    }

    public BookDtoShort mapToBookDtoShort(final Book book) {
        List<Long> recordsIdList = book.getBookRecords().stream()
                .map(record -> record.getRecordId())
                .collect(Collectors.toList());

        return new BookDtoShort(
                book.getTitleId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseDate(),
                recordsIdList
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> booksList) {
        return booksList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }


}
