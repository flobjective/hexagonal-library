package com.ffr.booklibrary.shared.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public abstract class BaseDomainEvent<T> {

  @JsonIgnore protected String eventName;

  @JsonIgnore protected UUID eventId;

  @JsonIgnore private Instant publishedDate;

  public void markPublished() {
    this.publishedDate = Instant.now();
  }

  public abstract T getEventPayload();
}
