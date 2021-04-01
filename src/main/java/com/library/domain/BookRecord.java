package com.library.domain;

enum Status {
    LOST, DESTROYED, RENTED
}

public final class BookRecord {

    private Long recordId;
    private Long bookId;
    private Status status;
}
