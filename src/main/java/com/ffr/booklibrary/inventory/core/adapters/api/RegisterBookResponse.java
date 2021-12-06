package com.ffr.booklibrary.inventory.core.adapters.api;

import com.ffr.booklibrary.inventory.core.domain.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

@AllArgsConstructor
@Getter
public class RegisterBookResponse {

  private String inventoryNumber;
  private String title;
  private String isbn10;
  private String isbn13;

  public static RegisterBookResponse of(final Book book) {
    val bookIdentification = book.bookIdentification();
    return new RegisterBookResponse(
        book.inventoryNumber().toString(),
        book.title().title(),
        bookIdentification.isbn10().value(),
        bookIdentification.isbn13().value());
  }
}
