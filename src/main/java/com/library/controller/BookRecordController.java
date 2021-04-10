package com.library.controller;

import com.library.domain.*;
import com.library.service.BookRecordDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class BookRecordController {

    private final BookRecordDbService service;

    @Autowired
    public BookRecordController(BookRecordDbService service) {
        this.service = service;
    }

    @PostMapping(value = "/bookRecords", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRecord(@RequestBody BookRecordDto bookRecordDto) throws BookNotFoundException {
        service.saveRecord(bookRecordDto);
    }

    @PutMapping(value = "/bookRecords/1")
    public BookRecordDto updateRecordStatus(@RequestParam Long recordId, @RequestParam Status status) throws BookRecordNotFoundException, BookNotFoundException {
        return service.updateRecord(recordId, status);
    }

    @GetMapping(value = "/bookRecords/1")
    public BookRecordDto getRecord(@RequestParam Long recordId) throws BookNotFoundException, BookRecordNotFoundException {
        return service.getRecord(recordId);
    }

    @GetMapping(value = "/bookRecords")
    public List<BookRecordDto> getAvailableRecords(@RequestParam String bookTitle) throws BookNotFoundException, BookRecordNotFoundException {
        return service.getAvailableRecordsByBookId(bookTitle);
    }
}
