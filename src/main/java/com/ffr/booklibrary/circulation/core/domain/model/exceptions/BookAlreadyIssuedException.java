package com.ffr.booklibrary.circulation.core.domain.model.exceptions;

public class BookAlreadyIssuedException extends RuntimeException {

  public BookAlreadyIssuedException() {
    super("Book has already been issued");
  }
}
