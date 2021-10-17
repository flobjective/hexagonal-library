package com.ffr.booklibrary.inventory.core.application.services;

import com.ffr.booklibrary.inventory.core.domain.model.Book;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookEventPublisher;

public class FakeBookEventPublisher implements BookEventPublisher {

    @Override
    public void bookAdded(Book book) {
        //
    }
}
