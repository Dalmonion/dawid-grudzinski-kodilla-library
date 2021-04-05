package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BooksRentalDto {
    private Long id;
    private final User user;
    private final BookRecord record;
    private final LocalDate rentFrom;
    private final LocalDate rentTo;

    public BooksRentalDto(User user, BookRecord record, LocalDate rentFrom, LocalDate rentTo) {
        this.user = user;
        this.record = record;
        this.rentFrom = rentFrom;
        this.rentTo = rentTo;
    }

    //    private final Long id;
//    private final Long userId;
//    private final Long recordId;
//    private final LocalDate rentFrom;
//    private final LocalDate rentTo;
}
