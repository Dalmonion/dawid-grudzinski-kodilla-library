package com.library.controller;


import com.library.domain.Book;
import com.library.domain.BookDto;
import com.library.mapper.BookMapper;
import com.library.service.BookDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library")
public class BookController {

    private final BookDbService service;
    private final BookMapper bookMapper;

    @Autowired
    public BookController(BookDbService service, BookMapper bookMapper) {
        this.service = service;
        this.bookMapper = bookMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        service.saveBook(book);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBookByTitle")
    public BookDto getBook(@RequestParam String bookTitle) {
        Book book = service.findBookByTitle(bookTitle);
        return bookMapper.mapToBookDto(book);
    }
}
