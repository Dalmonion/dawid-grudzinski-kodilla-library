package com.library.controller;


import com.library.domain.UserDto;
import com.library.domain.UserNotFoundException;
import com.library.mapper.UserMapper;
import com.library.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class UserController {

    private final UserDbService service;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserDbService service, UserMapper userMapper) {
        this.service = service;
        this.userMapper = userMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        service.saveUser(userDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return service.updateUser(userDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        service.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return service.getUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value= "getUsers")
    public List<UserDto> getUsers() {
        return service.getAllUsers();
    }
}
