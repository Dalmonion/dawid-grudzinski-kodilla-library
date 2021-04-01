package com.library;

import com.library.domain.Book;
import com.library.repository.BookRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class KodillaLibraryApplicationTests {

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

        @Autowired
        private BookRepository bookRepository;

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
}
