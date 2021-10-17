package com.ffr.booklibrary.inventory.core.adapters.api;

import com.ffr.booklibrary.inventory.core.domain.model.Book;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ListBookResponse {

    private List<BookListDto> books;

    public static ListBookResponse of(final List<Book> listBooks) {
        return ListBookResponse.builder()
                .books(listBooks.stream().map(BookListDto::of).collect(Collectors.toList()))
                .build();
    }
}
