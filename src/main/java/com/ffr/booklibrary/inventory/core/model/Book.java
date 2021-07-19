package com.ffr.booklibrary.inventory.core.model;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Accessors(fluent = true)
@Getter
@SuperBuilder(toBuilder = true)
public class Book extends BaseEntity {

    private InventoryNumber inventoryNumber;

    private final BookTitle title;

    private final BookIdentification bookIdentification;

    public static Book newBook(final Book book) {
        return book.toBuilder().id(UUID.randomUUID()).inventoryNumber(InventoryNumber.create()).build();
    }
}
