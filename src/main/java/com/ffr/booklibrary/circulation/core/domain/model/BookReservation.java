package com.ffr.booklibrary.circulation.core.domain.model;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Accessors(fluent = true)
public class BookReservation extends BaseEntity {

  private final UserId userId;

  private final Instant expirationTime;

  public static BookReservation create(final Clock clock, final UserId userId) {
    return BookReservation.builder()
        .userId(userId)
        .expirationTime(clock.instant().plus(Duration.ofDays(7)))
        .build();
  }

  public boolean hasExpired(final Clock nowClock) {
    return nowClock.instant().isAfter(expirationTime);
  }

  public boolean isReservedBy(final UserId userId) {
    return this.userId.equals(userId);
  }
}
