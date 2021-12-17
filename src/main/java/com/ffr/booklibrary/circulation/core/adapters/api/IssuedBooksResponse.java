package com.ffr.booklibrary.circulation.core.adapters.api;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IssuedBooksResponse {

  private List<Book> issuedBooks;

  public static IssuedBooksResponse of(
      final List<com.ffr.booklibrary.circulation.core.domain.model.Book> books) {
    return new IssuedBooksResponse(books.stream().map(Book::of).collect(Collectors.toList()));
  }
}
