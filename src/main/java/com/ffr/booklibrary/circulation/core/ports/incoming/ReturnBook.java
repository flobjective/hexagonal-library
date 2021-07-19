package com.ffr.booklibrary.circulation.core.ports.incoming;

public interface ReturnBook {

    void returnBook(ReturnBookCommand returnBookCommand);
}
