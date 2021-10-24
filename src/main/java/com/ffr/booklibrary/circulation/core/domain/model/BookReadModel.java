package com.ffr.booklibrary.circulation.core.domain.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class BookReadModel {

  private BookId bookId;

  private Instant expirationDate;
}
