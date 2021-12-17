package com.ffr.booklibrary.inventory.core.adapters.event;

import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookEventPublisher;
import com.ffr.booklibrary.shared.events.BaseDomainEvent;
import io.micronaut.context.event.ApplicationEventPublisher;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BookEventPublisherMicronautAdapter implements BookEventPublisher {

  @Inject private final ApplicationEventPublisher eventPublisher;

  public BookEventPublisherMicronautAdapter(final ApplicationEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  @Override
  // TODO: Do it in a @Async-fashion to decouple from other contexts and their potential problems :)
  public void publishEvents(final List<BaseDomainEvent> events) {
    for (var event : events) {
      this.eventPublisher.publishEvent(event);
    }
  }
}
