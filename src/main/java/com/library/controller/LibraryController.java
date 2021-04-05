package com.library.controller;

import com.library.domain.*;
import com.library.mapper.BookMapper;
import com.library.mapper.BookRecordMapper;
import com.library.mapper.BookRentalMapper;
import com.library.mapper.UserMapper;
import com.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    private final DbService service;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;
    private final BookRecordMapper bookRecordMapper;
    private final BookRentalMapper bookRentalMapper;

    @Autowired
    public LibraryController(DbService service, UserMapper userMapper, BookMapper bookMapper,
                             BookRecordMapper bookRecordMapper, BookRentalMapper bookRentalMapper) {
        this.service = service;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
        this.bookRecordMapper = bookRecordMapper;
        this.bookRentalMapper = bookRentalMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        service.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = service.saveUser(user);
        return userMapper.mapToUserDto(savedUser);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        service.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException{
        return userMapper.mapToUserDto(
                service.getUser(userId).orElseThrow(UserNotFoundException::new)
        );
    }

    @RequestMapping(method = RequestMethod.GET, value= "getUsers")
    public List<UserDto> getUsers() {
        List<User> users = service.getAllUsers();
        return userMapper.mapToUserDtoList(users);
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

    @RequestMapping(method = RequestMethod.POST, value = "createRecord", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRecord(@RequestBody BookRecordDto bookRecordDto) {
        BookRecord bookRecord = bookRecordMapper.mapToBookRecord(bookRecordDto);
        service.saveRecord(bookRecord);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateRecordStatus")
    public BookRecordDto updateRecordStatus(@RequestParam Long recordId, @RequestParam Status status) throws BookRecordNotFoundException {
        BookRecord bookRecord = service.getRecord(recordId).orElseThrow(BookRecordNotFoundException::new);
        bookRecord.setStatus(status);
        BookRecord savedRecord = service.saveRecord(bookRecord);
        return bookRecordMapper.mapToBookRecordDto(savedRecord);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRecord")
    public BookRecordDtoShort getRecord(@RequestParam Long recordId) throws BookRecordNotFoundException {
        return bookRecordMapper.mapToBookRecordDtoShort(
                service.getRecord(recordId).orElseThrow(BookRecordNotFoundException::new)
        );
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRecordLong")
    public BookRecordDto getRecordLong(@RequestParam Long recordId) throws BookRecordNotFoundException {
        return bookRecordMapper.mapToBookRecordDto(
                service.getRecord(recordId).orElseThrow(BookRecordNotFoundException::new)
        );
    }

    @RequestMapping(method = RequestMethod.GET, value= "getAllRecords")
    public List<BookRecordDtoShort> getRecords() {
        List<BookRecord> records = service.getAllRecords();
        return bookRecordMapper.mapToBookRecordDtoListShort(records);
    }

    @RequestMapping(method = RequestMethod.GET, value= "getAvailableRecords")
    public List<BookRecordDto> getAvailableRecords(@RequestParam String bookTitle) {
        BookDto bookDto = getBook(bookTitle);
        List<BookRecord> recordList = service.getAvailableRecordsByBookId(bookDto.getTitleId());
        return bookRecordMapper.mapToBookRecordDtoList(recordList);
    }

    @RequestMapping(method = RequestMethod.GET, value= "getQuantityAvailableRecords")
    public Integer getQuantityAvailableRecords() {
        List<BookRecord> recordList = service.getAvailableRecords();
        return recordList.size();
    }

    @RequestMapping(method = RequestMethod.POST, value = "rentTheBook")
    public void rentTheBook(@RequestParam Long userId, @RequestParam String bookTitle, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentUntil) throws BookRecordNotFoundException, UserNotFoundException {

            List<BookRecordDto> records = getAvailableRecords(bookTitle);
            if (!records.isEmpty()) {
                Long recordId = records.get(0).getRecordId();
                BookRecordDto recordDto = getRecordLong(recordId);

                UserDto userDto = getUser(userId);

                BooksRentalDto booksRentalDto = new BooksRentalDto(userMapper.mapToUser(userDto),
                        bookRecordMapper.mapToBookRecord(recordDto), LocalDate.now(), rentUntil);

                BooksRental booksRental = bookRentalMapper.mapToBooksRental(booksRentalDto);
                service.saveRental(booksRental);

                updateRecordStatus(recordId, Status.RENTED);
            }
    }
}
