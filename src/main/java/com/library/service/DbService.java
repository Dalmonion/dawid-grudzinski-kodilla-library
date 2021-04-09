package com.library.service;

import com.library.domain.*;
import com.library.mapper.BookMapper;
import com.library.mapper.BookRecordMapper;
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

//    public BookRecordDto getRecord(Long id) {
//        return bookRecordRepository.findById(id);
// }

    public Optional<BookRecord> getRecord(Long id) {
        return bookRecordRepository.findById(id);
    }

    public List<BookRecord> getAvailableRecords() {
        return bookRecordRepository.retrieveAvailableRecords();
    }

    public List<BookRecord> getAvailableRecordsByBookId(Long bookId) {
        return bookRecordRepository.getAvailableRecordsByBookId(bookId);
    }

    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public BooksRental saveRental(final BooksRental bookRental) {
        return booksRentalRepository.save(bookRental);
    }

    public Optional<BooksRental> getRentalRecord(Long id) {
        return booksRentalRepository.findById(id);
    }

    public void deleteRentalRecord(Long id) {
        booksRentalRepository.deleteById(id);
    }
}
