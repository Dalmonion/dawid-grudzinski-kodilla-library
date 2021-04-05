package com.library.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries(
        @NamedQuery(
                name = "BookRecord.retrieveAvailableRecords",
                query = "FROM BookRecord where status = 'AVAILABLE'"
        )
)
@NamedNativeQuery(
        name = "BookRecord.getAvailableRecordsByBookId",
        query = "SELECT * FROM BOOKS_RECORD WHERE BOOK_ID = :ID AND STATUS = 'AVAILABLE'",
        resultClass = BookRecord.class
)
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

    @JsonBackReference
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
