package com.ffr.booklibrary.core.inventory.core.model;

import java.util.UUID;

public class InventoryNumber {

    private UUID inventoryId;

    private InventoryNumber() {
        this.inventoryId = UUID.randomUUID();
    }

    public InventoryNumber(final String string) {
        this.inventoryId = UUID.fromString(string);
    }

    public static InventoryNumber create() {
        return new InventoryNumber();
    }

    @Override
    public String toString() {
        return this.inventoryId == null ? "<empty>" : this.inventoryId.toString();
    }
}
