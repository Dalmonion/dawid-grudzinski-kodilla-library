package com.library.controller;

import com.library.domain.*;
import com.library.mapper.BookRecordMapper;
import com.library.mapper.BookRentalMapper;
import com.library.mapper.UserMapper;
import com.library.service.BookRecordDbService;
import com.library.service.BookRentalDbService;
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

//    @RequestMapping(method = RequestMethod.POST, value = "createRecord", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void createRecord(@RequestBody BookRecordDto bookRecordDto) {
//        BookRecord bookRecord = bookRecordMapper.mapToBookRecord(bookRecordDto);
//        service.saveRecord(bookRecord);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "createRecord", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRecord(@RequestBody BookRecordDto bookRecordDto) {
        service.saveRecord(bookRecordDto);
    }

//    @RequestMapping(method = RequestMethod.POST, value = "createRecordShort", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void createRecordShort(@RequestBody BookRecordDtoShort bookRecordDtoShort) {
//        service.saveRecordShort(bookRecordDtoShort);
//    }

//    @RequestMapping(method = RequestMethod.PUT, value = "updateRecordStatus")
//    public BookRecordDto updateRecordStatus(@RequestParam Long recordId, @RequestParam Status status) throws BookRecordNotFoundException {
//        BookRecord bookRecord = service.getRecord(recordId).orElseThrow(BookRecordNotFoundException::new);
//        bookRecord.setStatus(status);
//        BookRecord savedRecord = service.saveRecord(bookRecord);
//        return bookRecordMapper.mapToBookRecordDto(savedRecord);
//    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateRecordStatus")
    public BookRecordDto updateRecordStatus(@RequestParam Long recordId, @RequestParam Status status) throws BookRecordNotFoundException {
        return service.updateRecord(recordId, status);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "getRecord")
//    public BookRecordDto getRecord(@RequestParam Long recordId) throws BookRecordNotFoundException {
//        return bookRecordMapper.mapToBookRecordDto(
//                service.getRecord(recordId).orElseThrow(BookRecordNotFoundException::new)
//        );
//    }

    @RequestMapping(method = RequestMethod.GET, value = "getRecord")
    public BookRecordDto getRecord(@RequestParam Long recordId) throws BookRecordNotFoundException {
        return service.getRecord(recordId);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "getRecordLong")
//    public BookRecordDto getRecordLong(@RequestParam Long recordId) throws BookRecordNotFoundException {
//        return bookRecordMapper.mapToBookRecordDto(
//                service.getRecord(recordId).orElseThrow(BookRecordNotFoundException::new)
//        );
//    }


//    @RequestMapping(method = RequestMethod.GET, value = "getAvailableRecords")
//    public List<BookRecordDto> getAvailableRecords(@RequestParam String bookTitle) {
//        BookDto bookDto = bookController.getBook(bookTitle);
//        List<BookRecord> recordList = service.getAvailableRecordsByBookId(bookDto.getTitleId());
//        return bookRecordMapper.mapToBookRecordDtoList(recordList);
//    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvailableRecords")
    public List<BookRecordDto> getAvailableRecords(@RequestParam String bookTitle) throws BookNotFoundException {
        return service.getAvailableRecordsByBookId(bookTitle);
    }

//    @RequestMapping(method = RequestMethod.POST, value = "rentTheBook")
//    public void rentTheBook(@RequestParam Long userId, @RequestParam String bookTitle,
//                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentUntil)
//            throws BookRecordNotFoundException, UserNotFoundException {
//
//        List<BookRecordDto> records = getAvailableRecords(bookTitle);
//        if (!records.isEmpty()) {
//            Long recordId = records.get(0).getRecordId();
//            BookRecordDto recordDto = getRecordLong(recordId);
//            UserDto userDto = userController.getUser(userId);
//            BooksRentalDto booksRentalDto = new BooksRentalDto(userMapper.mapToUser(userDto),
//                    bookRecordMapper.mapToBookRecord(recordDto), LocalDate.now(), rentUntil);
//
//            BooksRental booksRental = bookRentalMapper.mapToBooksRental(booksRentalDto);
//            bookRentalDbService.saveRental(booksRental);
//            updateRecordStatus(recordId, Status.RENTED);
//        }
//    }

    @RequestMapping(method = RequestMethod.POST, value = "rentTheBook")
    public void rentTheBook(@RequestParam Long userId, @RequestParam String bookTitle,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentUntil)
            throws BookRecordNotFoundException, UserNotFoundException, BookNotFoundException {
        service.rent(userId, bookTitle, rentUntil);
    }


}
