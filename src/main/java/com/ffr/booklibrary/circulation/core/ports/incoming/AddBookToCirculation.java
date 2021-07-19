package com.ffr.booklibrary.circulation.core.ports.incoming;

import com.ffr.booklibrary.circulation.core.model.InventoryNumber;

public interface AddBookToCirculation {
    void addBookToCirculation(InventoryNumber inventoryNumber);
}
