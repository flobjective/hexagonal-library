package com.ffr.booklibrary.core.inventory.core.ports.outgoing;

import com.ffr.booklibrary.core.inventory.core.model.Book;

public interface BookEventPublisher {
    void bookAdded(Book book);
}
