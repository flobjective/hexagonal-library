package com.ffr.booklibrary.circulation.core.application.ports.incoming;

public interface ReturnBook {

  void returnBook(ReturnBookCommand returnBookCommand);
}
