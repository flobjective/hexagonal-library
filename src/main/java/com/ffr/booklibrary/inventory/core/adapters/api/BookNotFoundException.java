package com.ffr.booklibrary.inventory.core.adapters.api;

public class BookNotFoundException extends RuntimeException {

  public BookNotFoundException(final String searchTerm) {
    super(String.format("Did not find any book for %s", searchTerm));
  }
}
