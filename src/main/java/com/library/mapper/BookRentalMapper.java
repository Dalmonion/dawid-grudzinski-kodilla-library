package com.library.mapper;


import com.library.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

//    public List<BooksRentalDto> mapToBooksrentalDtoList(final List<BooksRental> booksRentalList) {
//        return booksRentalList.stream()
//                .map(this::mapToBooksRentalDto)
//                .collect(Collectors.toList());
//    }
}
