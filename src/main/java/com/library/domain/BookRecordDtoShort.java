package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookRecordDtoShort {
    private final Long recordId;
    private final Status status;
    private Long bookId;
}
