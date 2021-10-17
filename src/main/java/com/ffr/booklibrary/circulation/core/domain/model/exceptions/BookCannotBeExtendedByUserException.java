package com.ffr.booklibrary.circulation.core.domain.model.exceptions;

public class BookCannotBeExtendedByUserException extends RuntimeException {

  public BookCannotBeExtendedByUserException() {
    super("Book cannot be extended by this user");
  }
}
