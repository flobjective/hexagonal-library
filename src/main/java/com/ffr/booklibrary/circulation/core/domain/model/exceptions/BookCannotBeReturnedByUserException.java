package com.ffr.booklibrary.circulation.core.domain.model.exceptions;

public class BookCannotBeReturnedByUserException extends RuntimeException {

  public BookCannotBeReturnedByUserException() {
    super("Book cannot be returned by this user");
  }
}
