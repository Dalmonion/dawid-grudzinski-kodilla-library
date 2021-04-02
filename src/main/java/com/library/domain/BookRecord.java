package com.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "BOOKS_RECORD")
public final class BookRecord {
    private Long recordId;
    private Long bookId;
    private Status status;

    public BookRecord() {
    }

    public BookRecord(Long bookId, Status status) {
        this.bookId = bookId;
        this.status = status;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "RECORD_ID", unique = true)
    public Long getRecordId() {
        return recordId;
    }

    @NotNull
    @Column(name = "BOOK_ID")
    public Long getBookId() {
        return bookId;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    public Status getStatus() {
        return status;
    }

    private void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    private void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    private void setStatus(Status status) {
        this.status = status;
    }
}
