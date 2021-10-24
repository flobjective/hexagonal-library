package com.ffr.booklibrary.inventory.core.adapters.api;

import com.ffr.booklibrary.inventory.core.domain.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

@AllArgsConstructor
@Getter
public class AddBookResponse {

  private String inventoryNumber;
  private String title;
  private String isbn10;
  private String isbn13;

  public static AddBookResponse of(final Book book) {
    val bookIdentification = book.bookIdentification();
    return new AddBookResponse(
        book.inventoryNumber().toString(),
        book.title().title(),
        bookIdentification.isbn10().value(),
        bookIdentification.isbn13().value());
  }
}
