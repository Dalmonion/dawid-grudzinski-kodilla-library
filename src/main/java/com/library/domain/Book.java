package com.library.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "BOOKS")
@AllArgsConstructor
@NoArgsConstructor
public final class Book {
    private Long titleId;
    private String title;
    private String author;
    private int releaseDate;
    private List<BookRecord> bookRecords;

    public Book(String title, String author, int releaseDate) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.bookRecords = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "TITLE_ID", unique = true)
    public Long getTitleId() {
        return titleId;
    }

    @Column(name = "TITLE")
    @NotNull
    public String getTitle() {
        return title;
    }

    @Column(name = "AUTHOR")
    @NotNull
    public String getAuthor() {
        return author;
    }

    @Column(name = "RELEASE_DATE")
    @NotNull
    public int getReleaseDate() {
        return releaseDate;
    }

    @JsonManagedReference
    @OneToMany(
            targetEntity = BookRecord.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<BookRecord> getBookRecords() {
        return bookRecords;
    }

    public void setBookRecords(List<BookRecord> bookRecords) {
        this.bookRecords = bookRecords;
    }

    private void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    private void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
}
