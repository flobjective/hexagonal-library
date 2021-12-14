package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.BookReadModel;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class IssuedBook {

  private String bookId;

  private Instant expirationDate;

  public static IssuedBook of(final BookReadModel book) {
    return new IssuedBook(book.bookId().toString(), book.expirationDate());
  }
}
