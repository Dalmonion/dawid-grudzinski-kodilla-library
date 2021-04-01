package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "BOOKS")
public final class Book {
    private Long titleId;
    private String title;
    private String author;
    private int releaseDate;

    public Book() {
    }

    public Book(String title, String author, int releaseDate) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
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
