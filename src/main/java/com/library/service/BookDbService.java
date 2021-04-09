package com.library.service;

import com.library.domain.Book;
import com.library.domain.BookDto;
import com.library.domain.BookDtoShort;
import com.library.domain.BookNotFoundException;
import com.library.mapper.BookMapper;
import com.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookDbService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public Book saveBook(final BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        return bookRepository.save(book);
    }

    public BookDto findBookByTitle(String title) throws BookNotFoundException{
        Book book = bookRepository.findByTitle(title).orElseThrow(BookNotFoundException::new);
        return bookMapper.mapToBookDto(book);
    }

    public BookDtoShort findBookByTitleShort(String title) throws BookNotFoundException {
        Book book = bookRepository.findByTitle(title).orElseThrow(BookNotFoundException::new);
        return bookMapper.mapToBookDtoShort(book);
    }

    public BookDto findBookById(Long bookId) throws BookNotFoundException {
        Book book = bookRepository.findByTitleId(bookId).orElseThrow(BookNotFoundException::new);
        return bookMapper.mapToBookDto(book);
    }

}
