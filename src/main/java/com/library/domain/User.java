package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public final class User {
    private Long userId;
    private String firstname;
    private String lastname;
    private LocalDate userCreationDate;
}
