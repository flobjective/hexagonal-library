package com.ffr.booklibrary.circulation.core.domain.model;

public class BookIsReservedException extends RuntimeException {
    public BookIsReservedException() {
        super("Book is reserved by another user.");
    }
}
