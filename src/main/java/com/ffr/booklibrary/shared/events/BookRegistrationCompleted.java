package com.ffr.booklibrary.shared.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

public class BookRegistrationCompleted extends BaseDomainEvent<BookRegistrationCompleted.BookAddedPayload> {

  public static final String EVENT_NAME = "BookAddedEvent";

  private BookAddedPayload payload;

  public BookRegistrationCompleted(
      final UUID eventId,
      final Instant publishedDate,
      final UUID bookId,
      final String inventoryNumber) {
    super(EVENT_NAME, eventId, publishedDate);
    this.payload = new BookAddedPayload(bookId, inventoryNumber);
  }

  public BookRegistrationCompleted(final UUID eventId, final Instant publishedDate, BookAddedPayload payload) {
    super(EVENT_NAME, eventId, publishedDate);
    this.payload = payload;
  }

  public static BookRegistrationCompleted create(final UUID bookId, final String inventoryNumber) {
    return new BookRegistrationCompleted(UUID.randomUUID(), null, bookId, inventoryNumber);
  }

  @Override
  public BookAddedPayload getEventPayload() {
    return this.payload;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  @Accessors(fluent = true)
  public static class BookAddedPayload {

    @JsonProperty private UUID bookId;

    @JsonProperty private String inventoryNumber;
  }
}
