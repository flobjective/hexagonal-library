package com.ffr.booklibrary.shared.events;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class BookRegistrationCompleted extends BaseDomainEvent {

  private final UUID bookId;
  private final String inventoryNumber;

  public BookRegistrationCompleted(
      final UUID eventId,
      final Instant publishedDate,
      final UUID bookId,
      final String inventoryNumber) {
    super(eventId, publishedDate);
    this.bookId = bookId;
    this.inventoryNumber = inventoryNumber;
  }

  public static BookRegistrationCompleted create(final UUID bookId, final String inventoryNumber) {
    return new BookRegistrationCompleted(UUID.randomUUID(), null, bookId, inventoryNumber);
  }
}
