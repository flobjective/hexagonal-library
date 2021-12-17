package com.ffr.booklibrary.shared.events;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public abstract class BaseDomainEvent {

  protected UUID eventId;

  private Instant publishedDate;

  public void markPublished() {
    this.publishedDate = Instant.now();
  }
}
