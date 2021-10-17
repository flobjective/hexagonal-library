package com.ffr.booklibrary.circulation.core.adapters.event;

import com.ffr.booklibrary.circulation.core.domain.model.InventoryNumber;
import com.ffr.booklibrary.circulation.core.application.ports.incoming.AddBookToCirculation;
import com.ffr.booklibrary.shared.events.BookAddedEvent;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.scheduling.annotation.Async;

import javax.inject.Singleton;

@Singleton
public class AddBookEventAdapter implements ApplicationEventListener<BookAddedEvent> {

    private AddBookToCirculation addBookToCirculation;

    public AddBookEventAdapter(final AddBookToCirculation addBookToCirculation) {
        this.addBookToCirculation = addBookToCirculation;
    }

    @Override
    @Async
    public void onApplicationEvent(final BookAddedEvent event) {
        addBookToCirculation.addBookToCirculation(new InventoryNumber(event.inventoryNumber()));
    }
}
