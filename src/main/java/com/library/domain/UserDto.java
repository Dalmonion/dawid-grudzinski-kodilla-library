package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public final class UserDto {
    private final Long userId;
    private final String firstname;
    private final String lastname;
    private final LocalDate userCreationDate;
}
