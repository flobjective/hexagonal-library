package com.ffr.booklibrary.circulation.core.domain.model;

import java.util.UUID;

public class InventoryNumber {

  private final UUID inventoryId;

  public InventoryNumber(final String string) {
    this.inventoryId = UUID.fromString(string);
  }

  @Override
  public String toString() {
    return this.inventoryId == null ? "" : this.inventoryId.toString();
  }
}
