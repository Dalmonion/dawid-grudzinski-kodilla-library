package com.library.controller;

import com.library.domain.*;
import com.library.service.BookRecordDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class BookRecordController {

    private final BookRecordDbService service;

    @Autowired
    public BookRecordController(BookRecordDbService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createRecord", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRecord(@RequestBody BookRecordDto bookRecordDto) throws BookNotFoundException{
        service.saveRecord(bookRecordDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateRecordStatus")
    public BookRecordDto updateRecordStatus(@RequestParam Long recordId, @RequestParam Status status) throws BookRecordNotFoundException, BookNotFoundException {
        return service.updateRecord(recordId, status);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRecord")
    public BookRecordDto getRecord(@RequestParam Long recordId) throws BookNotFoundException, BookRecordNotFoundException {
        return service.getRecord(recordId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvailableRecords")
    public List<BookRecordDto> getAvailableRecords(@RequestParam String bookTitle) throws BookNotFoundException, BookRecordNotFoundException {
        return service.getAvailableRecordsByBookId(bookTitle);
    }

    @RequestMapping(method = RequestMethod.POST, value = "rentTheBook")
    public void rentTheBook(@RequestParam Long userId, @RequestParam String bookTitle,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentUntil)
            throws BookRecordNotFoundException, UserNotFoundException, BookNotFoundException {
        service.rent(userId, bookTitle, rentUntil);
    }


}
