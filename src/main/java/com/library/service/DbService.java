package com.library.service;

import com.library.domain.Book;
import com.library.domain.BookRecord;
import com.library.domain.Status;
import com.library.domain.User;
import com.library.repository.BookRecordRepository;
import com.library.repository.BookRepository;
import com.library.repository.BooksRentalRepository;
import com.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookRecordRepository bookRecordRepository;
    private final BooksRentalRepository booksRentalRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public BookRecord saveRecord(final BookRecord bookRecord) {
        return bookRecordRepository.save(bookRecord);
    }

    public Optional<BookRecord> getRecord(Long id) {
        return bookRecordRepository.findById(id);
    }
}
