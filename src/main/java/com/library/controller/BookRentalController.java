package com.library.controller;

import com.library.domain.*;
import com.library.service.BookRentalDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/library")
public class BookRentalController {

    private final BookRentalDbService service;

    @Autowired
    public BookRentalController(BookRentalDbService bookRentalDbService) {
        this.service = bookRentalDbService;
    }

    @GetMapping(value = "rentalRecords")
    public BooksRentalDto getRentalRecord(@RequestParam Long rentRecordId) throws BookRentalRecordNotFoundException,
            UserNotFoundException, BookNotFoundException, BookRecordNotFoundException {
        return service.getRentalRecord(rentRecordId);
    }
    @DeleteMapping(value = "rentalRecords/1")
    public void returnTheBook(@RequestParam Long rentRecordId) throws BookRentalRecordNotFoundException,
            BookRecordNotFoundException, BookNotFoundException, UserNotFoundException {
        service.deleteRentalRecord(rentRecordId);
    }

    @PostMapping(value = "rentalRecords")
    public void rentTheBook(@RequestParam Long userId, @RequestParam String bookTitle,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentUntil)
            throws BookRecordNotFoundException, UserNotFoundException, BookNotFoundException {
        service.rent(userId, bookTitle, rentUntil);
    }

}
