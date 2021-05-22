package com.ffr.booklibrary.core.circulation.core.model;

import java.util.UUID;

public class InventoryNumber {

    private UUID inventoryId;

    public InventoryNumber(final String string) {
        this.inventoryId = UUID.fromString(string);
    }

    @Override
    public String toString() {
        return this.inventoryId == null ? "" : this.inventoryId.toString();
    }
}
