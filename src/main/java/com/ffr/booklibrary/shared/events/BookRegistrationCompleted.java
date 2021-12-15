package com.ffr.booklibrary.shared.events;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
public class BookRegistrationCompleted extends BaseDomainEvent {

  private UUID bookId;
  private String inventoryNumber;

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
