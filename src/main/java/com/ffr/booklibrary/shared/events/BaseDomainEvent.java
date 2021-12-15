package com.ffr.booklibrary.shared.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.UUID;

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
