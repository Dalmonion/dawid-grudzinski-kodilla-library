package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BooksRentalDto {
    private final Long id;
    private final User user;
    private final BookRecord record;
    private final LocalDate rentFrom;
    private final LocalDate rentTo;

//    private final Long id;
//    private final Long userId;
//    private final Long recordId;
//    private final LocalDate rentFrom;
//    private final LocalDate rentTo;
}
