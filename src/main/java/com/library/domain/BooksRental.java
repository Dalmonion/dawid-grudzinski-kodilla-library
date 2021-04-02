package com.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "BOOKS_RENTAL")
public final class BooksRental {
    private Long id;
    private BookRecord recordId;
    private User userId;
    private LocalDate rentFrom;
    private LocalDate rentTo;

    public BooksRental() {
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

    @OneToOne (cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "RECORD_ID")
    public BookRecord getRecordId() {
        return recordId;
    }

    @OneToOne (cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "USER_ID")
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setRecordId(BookRecord recordId) {
        this.recordId = recordId;
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
