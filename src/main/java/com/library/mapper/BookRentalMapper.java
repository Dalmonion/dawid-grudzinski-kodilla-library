package com.library.mapper;


import com.library.domain.BooksRental;
import com.library.domain.BooksRentalDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRentalMapper {
    public BooksRental mapToBooksRental(final BooksRentalDto booksRentalDto) {
        return new BooksRental(
                booksRentalDto.getId(),
                booksRentalDto.getUser(),
                booksRentalDto.getRecord(),
                booksRentalDto.getRentFrom(),
                booksRentalDto.getRentTo()
        );
    }

    public BooksRentalDto mapToBooksRentalDto(final BooksRental booksRental) {
        return new BooksRentalDto(
                booksRental.getId(),
                booksRental.getUserId(),
                booksRental.getRecordId(),
                booksRental.getRentFrom(),
                booksRental.getRentTo()
        );
    }

    public List<BooksRentalDto> mapToBooksrentalDtoList(final List<BooksRental> booksRentalList) {
        return booksRentalList.stream()
                .map(this::mapToBooksRentalDto)
                .collect(Collectors.toList());
    }
}
