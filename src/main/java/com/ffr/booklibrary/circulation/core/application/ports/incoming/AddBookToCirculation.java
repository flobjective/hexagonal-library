package com.ffr.booklibrary.circulation.core.application.ports.incoming;

import com.ffr.booklibrary.circulation.core.domain.model.InventoryNumber;

public interface AddBookToCirculation {
    void addBookToCirculation(InventoryNumber inventoryNumber);
}
