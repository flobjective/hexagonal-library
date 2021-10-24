package com.ffr.booklibrary.inventory.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class BookTitle {

  private final String title;
}
