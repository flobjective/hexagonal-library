package com.ffr.booklibrary.circulation.core.adapters.api;

import io.micronaut.http.hateoas.AbstractResource;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book extends AbstractResource<Book> {

  private String bookId;

  private String inventoryNumber;

  private boolean borrowable;

  public static Book of(final com.ffr.booklibrary.circulation.core.domain.model.Book book) {
    var bookId = book.id().toString();
    var resource =
        new Book(bookId, book.inventoryNumber().toString(), book.borrowable())
            .link("self", "/circulation/available/" + bookId);
    if (resource.borrowable) {
      resource.link("issue", "/circulation/available/" + bookId + "/issue");
    }
    return resource;
  }
}
