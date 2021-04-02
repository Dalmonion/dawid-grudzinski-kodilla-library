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
        void testBookRecordSave() {
            //Given
            BookRecord bookRecord = new BookRecord(2L, Status.RENTED);

            //When
            recordRepository.save(bookRecord);

            //Then
            Long id = bookRecord.getRecordId();
            Optional<BookRecord> readRecord = recordRepository.findById(id);
            assertTrue(readRecord.isPresent());

            //CleanUp
            recordRepository.deleteById(id);
        }
    }

    @Nested
    @DisplayName("Test for BooksRental objects")
    class BooksRentalTestSuite {

        @Test
        void testBookRentalSave() {
            //Given
            BooksRental booksRental = new BooksRental(1L, 2L, LocalDate.now(), LocalDate.now().plusDays(7));

            //When
            rentalRepository.save(booksRental);

            //Then
            Long id = booksRental.getId();
            Optional<BooksRental> readBooksRental = rentalRepository.findById(id);
            assertTrue(readBooksRental.isPresent());

            //CleanUp
//            rentalRepository.deleteById(id);
        }
    }
}
