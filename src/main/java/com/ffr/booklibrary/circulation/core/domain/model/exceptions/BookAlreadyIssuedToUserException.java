package com.ffr.booklibrary.circulation.core.domain.model.exceptions;

public class BookAlreadyIssuedToUserException extends RuntimeException {
  public BookAlreadyIssuedToUserException() {
    super("Book already issued to this user");
  }
}
