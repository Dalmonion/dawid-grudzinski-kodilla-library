package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class BookRecordDto {
    private final Long recordId;
    private final Status status;
    private final BookDto book;
}
