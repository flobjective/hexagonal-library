package com.ffr.booklibrary.inventory.core.adapters.db;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.EventsRepository;
import com.ffr.booklibrary.shared.events.BaseDomainEvent;
import com.ffr.booklibrary.shared.events.BookRegistrationCompleted;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Singleton;
import lombok.AllArgsConstructor;

@Singleton
@AllArgsConstructor
public class EventsJpaRepositoryAdapter implements EventsRepository {

  private EventsJpaRepository eventsJpaRepository;

  @Override
  public void save(final BaseDomainEvent event) {
    JsonNode node = new ObjectMapper().convertValue(event, JsonNode.class);
    String eventName = event.getClass().getSimpleName();
    if (eventsJpaRepository.existsById(event.eventId())) {
      eventsJpaRepository.update(
          JpaEvent.of(event.eventId(), eventName, event.publishedDate(), node));
    } else {
      eventsJpaRepository.save(
          JpaEvent.of(event.eventId(), eventName, event.publishedDate(), node));
    }
  }

  @Override
  public List<BaseDomainEvent> findAllUnpublished() {
    var mapper = new ObjectMapper();
    var unpublishedEvents = eventsJpaRepository.findAllUnpublished();
    return unpublishedEvents.stream()
        .map(
            (jpaEvent -> {
              switch (jpaEvent.eventName()) {
                case "BookRegistrationCompleted":
                  var bookRegistrationCompletedEvent =
                      mapper.convertValue(jpaEvent.eventPayload(), BookRegistrationCompleted.class);
                  return new BookRegistrationCompleted(
                      jpaEvent.id(),
                      jpaEvent.publishedDate(),
                      bookRegistrationCompletedEvent.bookId(),
                      bookRegistrationCompletedEvent.inventoryNumber());
                default:
                  return null;
              }
            }))
        .collect(Collectors.toList());
  }
}
