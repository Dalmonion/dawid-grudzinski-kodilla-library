package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate userCreationDate;
}
