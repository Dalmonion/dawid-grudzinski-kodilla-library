package com.library.controller;

import com.library.domain.User;
import com.library.domain.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {
    public void createUser(UserDto userDto) {

    }

    public UserDto updateUser(UserDto userDto) {
        return new UserDto(1L, "userFirstNameEdited", "userLastNameEdited", LocalDate.now());
    }

    public void deleteUser(Long userId) {

    }

    public UserDto getUser(Long userId) {
        return new UserDto(1L, "userFirstName", "userLastName", LocalDate.now());
    }

    @RequestMapping(method = RequestMethod.GET, value= "getUsers")
    public List<UserDto> getUsers() {
        return new ArrayList<>();
    }

}
