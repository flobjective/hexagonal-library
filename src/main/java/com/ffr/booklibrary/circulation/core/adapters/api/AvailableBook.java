package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.AvailableBookReadModel;
import io.micronaut.http.hateoas.AbstractResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailableBook extends AbstractResource<AvailableBook> {

  private String bookId;

  private String inventoryNumber;

  public static AvailableBook of(final AvailableBookReadModel availableBookReadModel) {
    var bookId = availableBookReadModel.bookId().toString();
    return new AvailableBook(
            bookId,
            availableBookReadModel.inventoryNumber().toString())
        .link("self", "/circulation/available/" + bookId)
            .link("issue", "/circulation/available/" + bookId + "/issue");
  }
}
