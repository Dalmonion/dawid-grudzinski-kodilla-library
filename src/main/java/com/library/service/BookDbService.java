package com.library.service;

import com.library.domain.Book;
import com.library.domain.BookDto;
import com.library.mapper.BookMapper;
import com.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookDbService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

//    public Book saveBook(final Book book) {
//        return bookRepository.save(book);
//    }

    public Book saveBook(final BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        return bookRepository.save(book);
    }

//    public Book findBookByTitle(String title) {
//        return bookRepository.findByTitle(title);
//    }
    public BookDto findBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title);
        return bookMapper.mapToBookDto(book);
    }

}
