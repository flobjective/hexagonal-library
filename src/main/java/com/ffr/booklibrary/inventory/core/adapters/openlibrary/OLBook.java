package com.ffr.booklibrary.inventory.core.adapters.openlibrary;

import com.ffr.booklibrary.inventory.core.domain.model.*;
import lombok.Data;

@Data
public class OLBook {

  private String title;

  private OLBookIdentifiers identifiers;

  public Book toBook() {
    return Book.builder()
        .title(new BookTitle(this.title))
        .bookIdentification(
            BookIdentification.builder()
                .isbn10(new Isbn10(identifiers.getIsbn10()))
                .isbn13(new Isbn13(identifiers.getIsbn13()))
                .build())
        .build();
  }
}
