package com.ffr.booklibrary.core.circulation.core.model;

public class BookAlreadyLentException extends RuntimeException {

    private final BookId bookId;

    public BookAlreadyLentException(final  BookId bookId) {
        this.bookId = bookId;
    }
}
