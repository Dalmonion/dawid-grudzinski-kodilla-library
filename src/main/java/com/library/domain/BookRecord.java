package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOOKS_RECORD")
public final class BookRecord {
    private Long recordId;
    private Status status;
    private Book book;

    public BookRecord(Status status) {
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
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    public Status getStatus() {
        return status;
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name = "BOOK_ID")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    private void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
