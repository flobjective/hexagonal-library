package com.ffr.booklibrary.core.inventory;

import com.ffr.booklibrary.core.inventory.core.model.Book;
import com.ffr.booklibrary.core.inventory.core.ports.outgoing.BookEventPublisher;

public class FakeBookEventPublisher implements BookEventPublisher {

    @Override
    public void bookAdded(Book book) {
        //
    }
}
