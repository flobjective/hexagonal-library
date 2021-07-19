package com.ffr.booklibrary.inventory.adapters.event;

import com.ffr.booklibrary.inventory.core.model.Book;
import com.ffr.booklibrary.inventory.core.ports.outgoing.BookEventPublisher;
import com.ffr.booklibrary.shared.events.BookAddedEvent;
import io.micronaut.context.event.ApplicationEventPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
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
