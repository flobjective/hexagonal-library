package com.ffr.booklibrary.circulation.core.adapters.api;

import io.micronaut.http.hateoas.AbstractResource;
import io.micronaut.http.hateoas.Link;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableBooksResponse extends AbstractResource<AvailableBooksResponse> {

  private List<Book> books;

  public static AvailableBooksResponse of(
      final List<com.ffr.booklibrary.circulation.core.domain.model.Book> books) {
    return new AvailableBooksResponse(books.stream().map(Book::of).collect(Collectors.toList()))
        .link(Link.SELF, Link.of("/circulation/books/available"))
        .link("find", Link.build("/circulation/books/{bookId}").templated(true).build());
  }
}
