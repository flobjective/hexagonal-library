package com.ffr.booklibrary.inventory.core.adapters.event;

import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookEventPublisher;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.EventsRepository;
import com.ffr.booklibrary.shared.events.BaseDomainEvent;
import io.micronaut.scheduling.annotation.Scheduled;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.inject.Singleton;
import lombok.AllArgsConstructor;

@Singleton
@AllArgsConstructor
public class BookEventPublisherTransactionalOutboxAdapter implements BookEventPublisher {

  private EventsRepository eventsRepository;

  private KafkaEventSender bookEventSender;

  @Override
  public void publishEvents(final List<BaseDomainEvent> events) {
    for (var event : events) {
      this.eventsRepository.save(event);
    }
  }

  @Scheduled(initialDelay = "20s", fixedDelay = "10s")
  void publishToBroker() {
    try {
      for (BaseDomainEvent event : this.eventsRepository.findAllUnpublished()) {
        bookEventSender.send(event).get();
        event.markPublished();
        this.eventsRepository.save(event);
      }
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    } catch (ExecutionException ex) {
      ex.printStackTrace();
    }
  }
}
