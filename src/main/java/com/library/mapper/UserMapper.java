package com.library.mapper;

import com.library.domain.User;
import com.library.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getUserCreationDate()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getFirstname(),
                user.getLastname(),
                user.getUserCreationDate()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
