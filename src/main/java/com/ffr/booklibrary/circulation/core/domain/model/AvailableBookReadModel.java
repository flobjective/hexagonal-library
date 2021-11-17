package com.ffr.booklibrary.circulation.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class AvailableBookReadModel {

  private BookId bookId;

  private InventoryNumber inventoryNumber;
}
