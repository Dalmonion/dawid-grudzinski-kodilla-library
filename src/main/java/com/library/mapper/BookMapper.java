package com.library.mapper;

import com.library.domain.Book;
import com.library.domain.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {
    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getTitleId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getReleaseDate(),
                bookDto.getBookRecords()
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getTitleId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseDate(),
                book.getBookRecords()
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> booksList) {
        return booksList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}
