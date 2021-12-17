package com.ffr.booklibrary.circulation.core.adapters.api;

import io.micronaut.http.hateoas.AbstractResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book extends AbstractResource<Book> {

  private String bookId;

  private String inventoryNumber;

  public static Book of(final com.ffr.booklibrary.circulation.core.domain.model.Book book) {
    var bookId = book.id().toString();
    return new Book(bookId, book.inventoryNumber().toString())
        .link("self", "/circulation/available/" + bookId)
        .link("issue", "/circulation/available/" + bookId + "/issue");
  }
}
