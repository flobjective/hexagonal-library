package com.ffr.booklibrary.circulation.core.adapters.event;

import com.ffr.booklibrary.circulation.core.application.ports.incoming.RegisterBookToCirculation;
import com.ffr.booklibrary.circulation.core.domain.model.InventoryNumber;
import com.ffr.booklibrary.shared.events.BookAddedEvent;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.scheduling.annotation.Async;
import javax.inject.Singleton;

@Singleton
public class RegisterBookEventAdapter implements ApplicationEventListener<BookAddedEvent> {

  private RegisterBookToCirculation registerBookToCirculation;

  public RegisterBookEventAdapter(final RegisterBookToCirculation registerBookToCirculation) {
    this.registerBookToCirculation = registerBookToCirculation;
  }

  @Override
  @Async
  public void onApplicationEvent(final BookAddedEvent event) {
    registerBookToCirculation.addBookToCirculation(
        new InventoryNumber(event.getEventPayload().inventoryNumber()));
  }
}
