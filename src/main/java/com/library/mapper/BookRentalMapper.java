package com.library.mapper;


import com.library.domain.*;
import org.springframework.stereotype.Service;

@Service
public class BookRentalMapper {
    public BooksRental mapToBooksRental(final BooksRentalDto booksRentalDto, User user, BookRecord bookRecord) {
        return new BooksRental(
                booksRentalDto.getId(),
                user,
                bookRecord,
                booksRentalDto.getRentFrom(),
                booksRentalDto.getRentTo()
        );
    }

    public BooksRentalDto mapToBooksRentalDto(final BooksRental booksRental, UserDto userDto, BookRecordDto bookRecordDto) {
        return new BooksRentalDto(
                booksRental.getId(),
                userDto,
                bookRecordDto,
                booksRental.getRentFrom(),
                booksRental.getRentTo()
        );
    }
}
