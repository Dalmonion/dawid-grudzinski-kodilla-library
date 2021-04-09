package com.library.service;

import com.library.domain.Book;
import com.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookDbService {

    private final BookRepository bookRepository;

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

}
