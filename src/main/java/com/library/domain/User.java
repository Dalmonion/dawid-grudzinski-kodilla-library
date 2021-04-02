package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "USERS")
public final class User {
    private Long userId;
    private String firstname;
    private String lastname;
    private LocalDate userCreationDate;

    public User() {
    }

    public User(String firstname, String lastname, LocalDate userCreationDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.userCreationDate = userCreationDate;
    }

    public User(Long userId, String firstname, String lastname, LocalDate userCreationDate) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userCreationDate = userCreationDate;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "USER_ID", unique = true)
    public Long getUserId() {
        return userId;
    }

    @NotNull
    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    @NotNull
    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    @NotNull
    @Column(name = "CREATED")
    public LocalDate getUserCreationDate() {
        return userCreationDate;
    }

    private void setUserId(Long userId) {
        this.userId = userId;
    }

    private void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private void setUserCreationDate(LocalDate userCreationDate) {
        this.userCreationDate = userCreationDate;
    }
}
