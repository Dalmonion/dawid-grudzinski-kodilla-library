package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
    private Long titleId;
    private String title;
    private String author;
    private int releaseDate;
}
