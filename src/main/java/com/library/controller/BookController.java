package com.library.controller;

import com.library.domain.BookDto;
import com.library.domain.BookNotFoundException;
import com.library.service.BookDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library")
public class BookController {

    private final BookDbService service;

    @Autowired
    public BookController(BookDbService service) {
        this.service = service;
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto) {
        service.saveBook(bookDto);
    }

    @GetMapping(value = "/books/1")
    public BookDto getBookById(@RequestParam Long bookId) throws BookNotFoundException {
        return service.findBookById(bookId);
    }
}
