package com.ffr.booklibrary.circulation.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.Instant;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class BookReadModel {

    private BookId bookId;

    private Instant expirationDate;
}
