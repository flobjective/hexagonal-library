package com.ffr.booklibrary.inventory.core.application.services;

import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookEventPublisher;
import com.ffr.booklibrary.shared.events.BaseDomainEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FakeBookEventPublisher implements BookEventPublisher {

  private final List<BaseDomainEvent> publishedEvents = new ArrayList<>();

  @Override
  public void publishEvents(List<BaseDomainEvent> events) {
    this.publishedEvents.addAll(events);
  }

  public boolean containsEvent(Predicate<BaseDomainEvent> predicate) {
    return this.publishedEvents.stream().anyMatch(predicate);
  }
}
