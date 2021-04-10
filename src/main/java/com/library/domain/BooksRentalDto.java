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

}
