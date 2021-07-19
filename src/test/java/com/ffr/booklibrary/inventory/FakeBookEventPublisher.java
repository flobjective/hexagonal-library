package com.ffr.booklibrary.inventory;

import com.ffr.booklibrary.inventory.core.model.Book;
import com.ffr.booklibrary.inventory.core.ports.outgoing.BookEventPublisher;

public class FakeBookEventPublisher implements BookEventPublisher {

    @Override
    public void bookAdded(Book book) {
        //
    }
}
