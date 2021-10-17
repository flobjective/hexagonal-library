package com.ffr.booklibrary.inventory.core.adapters.event;

import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookEventPublisher;
import com.ffr.booklibrary.inventory.core.domain.model.Book;
import com.ffr.booklibrary.shared.events.BookAddedEvent;
import io.micronaut.context.event.ApplicationEventPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BookEventPublisherKafkaAdapter implements BookEventPublisher {

    @Inject
    private ApplicationEventPublisher eventPublisher;

    public BookEventPublisherKafkaAdapter(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void bookAdded(final Book book) {
        // TODO: implement
    }
}
