package com.library;

import com.library.domain.*;
import com.library.repository.BookRecordRepository;
import com.library.repository.BookRepository;

import static org.junit.jupiter.api.Assertions.*;

import com.library.repository.BooksRentalRepository;
import com.library.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class KodillaLibraryApplicationTests {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRecordRepository recordRepository;
    @Autowired
    private BooksRentalRepository rentalRepository;

    @Nested
    @DisplayName("General tests")
    class GeneralTestSuite {
        @Test
        void contextLoads() {
        }
    }

    @Nested
    @DisplayName("Test for Book objects")
    class BookTestSuite {

        @Test
        void testBookSave() {
            //Given
            Book book = new Book("titleTest", "authorTest", 2000);

            //When
            bookRepository.save(book);

            //Then
            Long id = book.getTitleId();
            Optional<Book> readBook = bookRepository.findById(id);
            assertTrue(readBook.isPresent());

            //CleanUp
            bookRepository.deleteById(id);
        }
    }

    @Nested
    @DisplayName("Test for User objects")
    class UserTestSuite {

        @Test
        void testUserSave() {
            //Given
            User user = new User("firstNameTest", "LastNameTest", LocalDate.now());

            //When
            userRepository.save(user);

            //Then
            Long id = user.getUserId();
            Optional<User> readUser = userRepository.findById(id);
            assertTrue(readUser.isPresent());

            //CleanUp
            userRepository.deleteById(id);
        }
    }

    @Nested
    @DisplayName("Test for BookRecord objects")
    class BookRecordTestSuite {

        @Test
        void testBookSaveWithBookRecords() {
            //Given
            BookRecord bookRecord1 = new BookRecord(Status.AVAILABLE);
            BookRecord bookRecord2 = new BookRecord(Status.AVAILABLE);
            BookRecord bookRecord3 = new BookRecord(Status.DESTROYED);
            BookRecord bookRecord4 = new BookRecord(Status.RENTED);
            BookRecord bookRecord5 = new BookRecord(Status.RENTED);
            Book book = new Book("titleTest", "authorTest", 2001);
            Book book2 = new Book("titleTest2", "authorTest2", 200122);
            book.getBookRecords().add(bookRecord1);
            book.getBookRecords().add(bookRecord2);
            book.getBookRecords().add(bookRecord3);
            book2.getBookRecords().add(bookRecord4);
            book2.getBookRecords().add(bookRecord5);
            bookRecord1.setBook(book);
            bookRecord2.setBook(book);
            bookRecord3.setBook(book);
            bookRecord4.setBook(book2);
            bookRecord5.setBook(book2);

            //When
            bookRepository.save(book);
            bookRepository.save(book2);

            //Then
            Long id = book.getTitleId();
            Long id2 = book2.getTitleId();
            assertNotEquals(0, id);
            assertNotEquals(0, id2);


            //CleanUp
            bookRepository.deleteById(id);
            bookRepository.deleteById(id2);
        }
    }

    @Nested
    @DisplayName("Test for BooksRental objects")
    class BooksRentalTestSuite {

        @Transient
        @Test
        void testBookRentalSave() {
            //Given
            Book book = new Book("titleTest123", "authorTest123", 123);
            BookRecord bookRecord = new BookRecord(Status.AVAILABLE);
            bookRecord.setBook(book);
            book.getBookRecords().add(bookRecord);
            User user = new User("firstName123", "lastname123", LocalDate.now());
            BooksRental booksRental = new BooksRental(LocalDate.now(), LocalDate.now().plusMonths(1));
            booksRental.setRecordId(bookRecord);
            booksRental.setUserId(user);

            //When
            bookRepository.save(book);
            userRepository.save(user);
            rentalRepository.save(booksRental);

            //Then
            Long rentalId = booksRental.getId();
            Long bookId = book.getTitleId();
            Long userId = user.getUserId();

            Optional<BooksRental> readBooksRental = rentalRepository.findById(rentalId);
            assertTrue(readBooksRental.isPresent());

            //CleanUp
            rentalRepository.deleteById(rentalId);
            bookRepository.deleteById(bookId);
            userRepository.deleteById(userId);
        }
    }
}
