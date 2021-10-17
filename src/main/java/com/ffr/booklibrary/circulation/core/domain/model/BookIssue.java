package com.ffr.booklibrary.circulation.core.domain.model;

import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookCannotBeRenewedException;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder()
@Getter
@Accessors(fluent = true)
public class BookIssue extends BaseEntity {

  private final UserId userId;

  private final Instant expirationTime;

  private final int maximumRenewals;

  private final List<BookRenewal> renewals = new ArrayList<>();

  public boolean hasElapsed(final Instant currentTime) {
    return expirationTime
        .plus(
            renewals.stream()
                .map(BookRenewal::renewalDuration)
                .reduce(Duration.ofDays(0), Duration::plus))
        .isAfter(currentTime);
  }

  public void renew(final Clock clock) {
    if (!this.hasReachedMaximumRenewals() || !hasElapsed(clock.instant())) {
      throw new BookCannotBeRenewedException();
    }
    this.renewals.add(new BookRenewal(Duration.ofDays(7)));
  }

  private boolean hasReachedMaximumRenewals() {
    return this.renewals.size() < this.maximumRenewals;
  }

  public static BookIssue createIssue(final UserId userId, final Instant expirationTime) {
    return BookIssue.builder()
        .userId(userId)
        .maximumRenewals(1)
        .expirationTime(expirationTime)
        .build();
  }
}
