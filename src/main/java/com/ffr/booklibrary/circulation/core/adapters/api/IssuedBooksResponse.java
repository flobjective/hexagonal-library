package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.BookReadModel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IssuedBooksResponse {

  private List<IssuedBook> issuedBooks;

  public static IssuedBooksResponse of(final List<BookReadModel> books) {
    return new IssuedBooksResponse(
        books.stream().map(IssuedBook::of).collect(Collectors.toList()));
  }
}
