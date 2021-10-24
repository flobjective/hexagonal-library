package com.ffr.booklibrary.circulation.core.domain.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class BookId {

  private UUID id;
}
