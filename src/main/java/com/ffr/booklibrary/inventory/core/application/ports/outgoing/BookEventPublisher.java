package com.ffr.booklibrary.inventory.core.application.ports.outgoing;

import com.ffr.booklibrary.shared.events.BaseDomainEvent;
import java.util.List;

public interface BookEventPublisher {
  void publishEvents(final List<BaseDomainEvent> events);
}
