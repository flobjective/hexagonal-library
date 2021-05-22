package com.ffr.booklibrary.core.inventory.adapters.event;

import com.ffr.booklibrary.core.inventory.core.model.Book;
import com.ffr.booklibrary.core.inventory.core.ports.outgoing.BookEventPublisher;
import com.ffr.booklibrary.core.shared.events.BookAddedEvent;
import io.micronaut.context.event.ApplicationEventPublisher;

import javax.inject.Inject;

public class BookEventPublisherMicronautAdapter implements BookEventPublisher {

    @Inject
    private ApplicationEventPublisher eventPublisher;

    public BookEventPublisherMicronautAdapter(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void bookAdded(final Book book) {
        this.eventPublisher.publishEvent(new BookAddedEvent(book.id(), book.inventoryNumber().toString()));
    }
}
