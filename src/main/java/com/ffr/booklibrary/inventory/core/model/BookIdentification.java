package com.ffr.booklibrary.inventory.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
@Builder
public class BookIdentification {

    private final Isbn10 isbn10;

    private final Isbn13 isbn13;
}
