package com.ffr.booklibrary.core.circulation.core.ports.incoming;

import com.ffr.booklibrary.core.circulation.core.model.BookId;
import com.ffr.booklibrary.core.circulation.core.model.InventoryNumber;

public interface AddBookToCirculation {
    void addBookToCirculation(BookId bookId, InventoryNumber inventoryNumber);
}
