package com.ffr.booklibrary.core.circulation.adapters.event;

import com.ffr.booklibrary.core.circulation.core.model.BookId;
import com.ffr.booklibrary.core.circulation.core.model.InventoryNumber;
import com.ffr.booklibrary.core.circulation.core.ports.incoming.AddBookToCirculation;
import com.ffr.booklibrary.core.shared.events.BookAddedEvent;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.scheduling.annotation.Async;

public class AddBookEventAdapter implements ApplicationEventListener<BookAddedEvent> {

    private AddBookToCirculation addBookToCirculation;

    public AddBookEventAdapter(AddBookToCirculation addBookToCirculation) {
        this.addBookToCirculation = addBookToCirculation;
    }

    @Override
    @Async
    public void onApplicationEvent(BookAddedEvent event) {
        addBookToCirculation.addBookToCirculation(new BookId(event.bookId()), new InventoryNumber(event.inventoryNumber()));
    }
}
