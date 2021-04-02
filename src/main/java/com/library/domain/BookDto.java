package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public final class BookDto {
    private final Long titleId;
    private final String title;
    private final String author;
    private final int releaseDate;
    private final List<BookRecord> bookRecords;
}


