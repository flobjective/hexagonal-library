package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.BookReadModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

@AllArgsConstructor
@Data
public class IssuedBookDto {

    private String bookId;

    private Instant expirationDate;

    public static IssuedBookDto of(final BookReadModel book) {
        return new IssuedBookDto(book.bookId().toString(), book.expirationDate());
    }
}
