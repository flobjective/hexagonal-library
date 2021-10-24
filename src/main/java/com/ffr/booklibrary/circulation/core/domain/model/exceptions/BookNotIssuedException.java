package com.ffr.booklibrary.circulation.core.domain.model.exceptions;

public class BookNotIssuedException extends RuntimeException {

  public BookNotIssuedException() {
    super("Book is not issued");
  }
}
