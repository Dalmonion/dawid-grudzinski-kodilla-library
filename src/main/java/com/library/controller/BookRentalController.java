package com.library.controller;

import com.library.domain.*;
import com.library.mapper.BookRentalMapper;
import com.library.service.BookRentalDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/library")
public class BookRentalController {

    private final BookRentalDbService service;

    @Autowired
    public BookRentalController(BookRentalDbService bookRentalDbService) {
        this.service = bookRentalDbService;
    }

//    @RequestMapping(method = RequestMethod.GET, value = "getRentalRecord")
//    public BooksRentalDto getRentalRecord(@RequestParam Long rentRecordId) throws BookRentalRecordNotFoundException {
//        return bookRentalMapper.mapToBooksRentalDto(service.getRentalRecord(rentRecordId).
//                orElseThrow(BookRentalRecordNotFoundException::new));
//    }

    @RequestMapping(method = RequestMethod.GET, value = "getRentalRecord")
    public BooksRentalDto getRentalRecord(@RequestParam Long rentRecordId) throws BookRentalRecordNotFoundException, UserNotFoundException, BookNotFoundException, BookRecordNotFoundException {
        return service.getRentalRecord(rentRecordId);
    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "returnBook")
//    public void returnTheBook(@RequestParam Long rentRecordId) throws BookRentalRecordNotFoundException, BookRecordNotFoundException {
//        BooksRentalDto bookRentalRecord = getRentalRecord(rentRecordId);
//        bookRecordController.updateRecordStatus(bookRentalRecord.getRecord().getRecordId(), Status.AVAILABLE);
//        service.deleteRentalRecord(rentRecordId);
//    }

    @RequestMapping(method = RequestMethod.DELETE, value = "returnBook")
    public void returnTheBook(@RequestParam Long rentRecordId) throws BookRentalRecordNotFoundException, BookRecordNotFoundException, BookNotFoundException, UserNotFoundException {
        service.deleteRentalRecord(rentRecordId);
    }

}
