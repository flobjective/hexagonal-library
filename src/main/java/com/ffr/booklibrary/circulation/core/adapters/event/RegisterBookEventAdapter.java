package com.ffr.booklibrary.circulation.core.adapters.event;

import com.ffr.booklibrary.circulation.core.application.ports.incoming.RegisterBookToCirculation;
import com.ffr.booklibrary.circulation.core.domain.model.InventoryNumber;
import com.ffr.booklibrary.shared.events.BookRegistrationCompleted;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.scheduling.annotation.Async;
import javax.inject.Singleton;

@Singleton
public class RegisterBookEventAdapter
    implements ApplicationEventListener<BookRegistrationCompleted> {

  private final RegisterBookToCirculation registerBookToCirculation;

  public RegisterBookEventAdapter(final RegisterBookToCirculation registerBookToCirculation) {
    this.registerBookToCirculation = registerBookToCirculation;
  }

  @Override
  @Async
  public void onApplicationEvent(final BookRegistrationCompleted event) {
    registerBookToCirculation.addBookToCirculation(new InventoryNumber(event.inventoryNumber()));
  }
}
