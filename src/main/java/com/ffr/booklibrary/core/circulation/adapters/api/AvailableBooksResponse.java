package com.ffr.booklibrary.core.circulation.adapters.api;

import com.ffr.booklibrary.core.circulation.core.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class AvailableBooksResponse {

    private List<AvailableBookDto> availableBooks;

    public static AvailableBooksResponse of(final List<Book> books) {
        return new AvailableBooksResponse(books.stream().map(AvailableBookDto::of).collect(Collectors.toList()));
    }
}
