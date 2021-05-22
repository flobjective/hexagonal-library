package com.ffr.booklibrary.core.circulation.adapters.api;

import com.ffr.booklibrary.core.circulation.core.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Data
@Jacksonized
public class AvailableBookDto {

    private String bookId;

    public static AvailableBookDto of(final Book book) {
        return new AvailableBookDto(book.bookId().id().toString());
    }
}
