package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.AvailableBookReadModel;
import io.micronaut.http.hateoas.AbstractResource;
import io.micronaut.http.hateoas.Link;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableBooksResponse extends AbstractResource<AvailableBooksResponse> {

  private List<AvailableBook> availableBooks;

  public static AvailableBooksResponse of(final List<AvailableBookReadModel> books) {
    return new AvailableBooksResponse(
            books.stream().map(AvailableBook::of).collect(Collectors.toList()))
        .link(Link.SELF, Link.of("/circulation/available"))
        .link("find", Link.build("/circulation/available/{bookId}").templated(true).build());
  }
}
