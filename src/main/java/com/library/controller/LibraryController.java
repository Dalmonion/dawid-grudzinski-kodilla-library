package com.library.controller;

import com.library.domain.*;
import com.library.mapper.BookMapper;
import com.library.mapper.UserMapper;
import com.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public LibraryController(DbService service, UserMapper userMapper, BookMapper bookMapper) {
        this.service = service;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
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
}
