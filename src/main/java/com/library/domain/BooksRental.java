package com.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "BOOKS_RENTAL")
public final class BooksRental {
    private Long id;
    private Long recordId;
    private Long userId;
    private LocalDate rentFrom;
    private LocalDate rentTo;

    public BooksRental() {
    }

    public BooksRental(Long recordId, Long userId, LocalDate rentFrom, LocalDate rentTo) {
        this.recordId = recordId;
        this.userId = userId;
        this.rentFrom = rentFrom;
        this.rentTo = rentTo;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @NotNull
    @Column(name = "RECORD_ID")
    public Long getRecordId() {
        return recordId;
    }

    @NotNull
    @Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    @NotNull
    @Column(name = "RENTED_FROM")
    public LocalDate getRentFrom() {
        return rentFrom;
    }

    @NotNull
    @Column(name = "RENTED_TO")
    public LocalDate getRentTo() {
        return rentTo;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    private void setUserId(Long userId) {
        this.userId = userId;
    }

    private void setRentFrom(LocalDate rentFrom) {
        this.rentFrom = rentFrom;
    }

    private void setRentTo(LocalDate rentTo) {
        this.rentTo = rentTo;
    }
}
