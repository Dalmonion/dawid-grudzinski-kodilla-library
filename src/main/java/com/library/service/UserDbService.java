package com.library.service;

import com.library.domain.User;
import com.library.domain.UserDto;
import com.library.domain.UserNotFoundException;
import com.library.mapper.UserMapper;
import com.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.mapToUserDtoList(users);
    }

    public UserDto getUser(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        return userMapper.mapToUserDto(user.orElseThrow(UserNotFoundException::new));
    }

    public User saveUser(final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        return userRepository.save(user);
    }

    public UserDto updateUser(final UserDto userDto) {
        User savedUser = saveUser(userDto);
        return userMapper.mapToUserDto(savedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
