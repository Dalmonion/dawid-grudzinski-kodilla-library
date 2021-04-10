package com.library.controller;


import com.library.domain.UserDto;
import com.library.domain.UserNotFoundException;
import com.library.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class UserController {

    private final UserDbService service;

    @Autowired
    public UserController(UserDbService service) {
        this.service = service;
    }

    @PostMapping(value = "users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        service.saveUser(userDto);
    }

    @PutMapping(value = "users/1", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return service.updateUser(userDto);
    }

    @DeleteMapping(value = "users/1")
    public void deleteUser(@RequestParam Long userId) {
        service.deleteUser(userId);
    }

    @GetMapping(value = "users/1")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return service.getUser(userId);
    }

    @GetMapping(value = "users")
    public List<UserDto> getUsers() {
        return service.getAllUsers();
    }
}
