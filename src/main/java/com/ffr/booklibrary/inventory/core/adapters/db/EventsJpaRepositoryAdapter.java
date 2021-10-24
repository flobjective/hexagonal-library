package com.ffr.booklibrary.inventory.core.adapters.db;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.EventsRepository;
import com.ffr.booklibrary.shared.events.BaseDomainEvent;
import com.ffr.booklibrary.shared.events.BookAddedEvent;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.inject.Singleton;
import lombok.AllArgsConstructor;

@Singleton
@AllArgsConstructor
public class EventsJpaRepositoryAdapter implements EventsRepository {

  private EventsJpaRepository eventsJpaRepository;

  @Override
  public void save(final BaseDomainEvent event) {
    JsonNode node = new ObjectMapper().convertValue(event.getEventPayload(), JsonNode.class);
    if (eventsJpaRepository.existsById(event.eventId())) {
      eventsJpaRepository.update(
          JpaEvent.of(event.eventId(), event.eventName(), event.publishedDate(), node));
    } else {
      eventsJpaRepository.save(
          JpaEvent.of(event.eventId(), event.eventName(), event.publishedDate(), node));
    }
  }

  @Override
  public List<BaseDomainEvent> findAllUnpublished() {
    var mapper = new ObjectMapper();
    var unpublishedEvents = eventsJpaRepository.findAllUnpublished();
    return StreamSupport.stream(unpublishedEvents.spliterator(), false)
        .map(
            (jpaEvent -> {
              switch (jpaEvent.eventName()) {
                case "BookAddedEvent":
                  var eventPayload =
                      mapper.convertValue(
                          jpaEvent.eventPayload(), BookAddedEvent.BookAddedPayload.class);
                  return new BookAddedEvent(jpaEvent.id(), jpaEvent.publishedDate(), eventPayload);
                default:
                  return null;
              }
            }))
        .collect(Collectors.toList());
  }
}
