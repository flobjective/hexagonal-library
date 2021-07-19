package com.ffr.booklibrary.inventory.core.ports.outgoing;

import com.ffr.booklibrary.inventory.core.model.Book;

public interface BookEventPublisher {
    void bookAdded(Book book);
}
