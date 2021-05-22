package com.ffr.booklibrary.core.inventory.adapters.openlibrary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ffr.booklibrary.core.inventory.core.model.*;
import lombok.Data;

@Data
public class OLBook {

    private String title;

    private OLBookIdentifiers identifiers;

    public Book toBook() {
        return Book.builder().title(new BookTitle(this.title))
                .bookIdentification(
                        BookIdentification.builder()
                                .isbn10(new Isbn10(identifiers.getIsbn10()))
                                .isbn13(new Isbn13(identifiers.getIsbn13()))
                                .build())
                .build();
    }
}
