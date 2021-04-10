package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "BOOKS_RENTAL")
@AllArgsConstructor
@NoArgsConstructor
public final class BooksRental {
    private Long id;
    private User user;
    private BookRecord record;
    private LocalDate rentFrom;
    private LocalDate rentTo;

    public BooksRental(User user, BookRecord record, LocalDate rentFrom, LocalDate rentTo) {
        this.user = user;
        this.record = record;
        this.rentFrom = rentFrom;
        this.rentTo = rentTo;
    }

    public BooksRental(LocalDate rentFrom, LocalDate rentTo) {
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
    @Column(name = "RENTED_FROM")
    public LocalDate getRentFrom() {
        return rentFrom;
    }

    @NotNull
    @Column(name = "RENTED_TO")
    public LocalDate getRentTo() {
        return rentTo;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RECORD_ID")
    public BookRecord getRecordId() {
        return record;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User getUserId() {
        return user;
    }

    public void setUserId(User userId) {
        this.user = userId;
    }

    public void setRecordId(BookRecord recordId) {
        this.record = recordId;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setRentFrom(LocalDate rentFrom) {
        this.rentFrom = rentFrom;
    }

    private void setRentTo(LocalDate rentTo) {
        this.rentTo = rentTo;
    }
}
