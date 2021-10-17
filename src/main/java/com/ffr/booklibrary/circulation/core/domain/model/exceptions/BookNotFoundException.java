package com.ffr.booklibrary.circulation.core.domain.model.exceptions;

import com.ffr.booklibrary.circulation.core.domain.model.BookId;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(final BookId id) {
        super(String.format("Book with id: %s not found", id.toString()));
    }
}
