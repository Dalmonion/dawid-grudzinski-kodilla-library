package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class BooksRentalDto {
    private Long id;
    private final UserDto userDto;
    private final BookRecordDto recordDto;
    private final LocalDate rentFrom;
    private final LocalDate rentTo;

//    public BooksRentalDto(UserDto userDto, BookRecordDto recordDto, LocalDate rentFrom, LocalDate rentTo) {
//        this.userDto = userDto;
//        this.recordDto = recordDto;
//        this.rentFrom = rentFrom;
//        this.rentTo = rentTo;
//    }

    //    private final Long id;
//    private final Long userId;
//    private final Long recordId;
//    private final LocalDate rentFrom;
//    private final LocalDate rentTo;
}
