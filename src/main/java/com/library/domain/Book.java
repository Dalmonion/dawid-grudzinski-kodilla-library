package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
    private Long titleId;
    private String title;
    private String author;
    private int releaseDate;
}
