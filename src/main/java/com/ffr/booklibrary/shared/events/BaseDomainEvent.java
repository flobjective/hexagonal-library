package com.ffr.booklibrary.shared.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public abstract class BaseDomainEvent<T> {

  @JsonProperty
  protected String eventName;

  protected UUID eventId;

  private Instant publishedDate;

  public void markPublished() {
    this.publishedDate = Instant.now();
  }

  public abstract T getEventPayload();
}
