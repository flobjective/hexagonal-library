package com.ffr.booklibrary.inventory.core.application.ports.outgoing;

import com.ffr.booklibrary.inventory.core.domain.model.Book;

public interface BookEventPublisher {
    void bookAdded(Book book);
}
