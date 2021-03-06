package com.ffr.booklibrary.inventory.core.adapters.api;

import com.ffr.booklibrary.inventory.core.domain.model.Book;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookList {

  private String id;

  private String isbn10;

  private String isbn13;

  private String title;

  public static BookList of(final Book book) {
    return BookList.builder()
        .id(book.id().toString())
        .title(book.title().title())
        .isbn10(book.bookIdentification().isbn10().value())
        .isbn13(book.bookIdentification().isbn13().value())
        .build();
  }
}
