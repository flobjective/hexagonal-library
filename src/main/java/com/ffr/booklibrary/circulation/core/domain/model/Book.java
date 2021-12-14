package com.ffr.booklibrary.circulation.core.domain.model;

import com.ffr.booklibrary.circulation.core.domain.model.exceptions.*;
import java.time.Clock;
import java.time.Duration;

import com.ffr.booklibrary.shared.events.BaseDomainEvent;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Accessors(fluent = true)
public class Book extends BaseEntity {

  private InventoryNumber inventoryNumber;
  private BookIssue currentBookIssue;
  private BookReservation currentReservation;
  private Clock clock;

  public static Book create(final Clock clock, final InventoryNumber inventoryNumber) {
    return Book.builder().clock(clock).inventoryNumber(inventoryNumber).build();
  }

  public BookIssue issueToUser(final UserId userId) {
    if (isIssued()) {
      throw new BookAlreadyIssuedException();
    }
    if (isReserved()) {
      if (this.currentReservation.hasExpired(this.clock)) {
        expireReservation();
      } else if (this.currentReservation.isReservedBy(userId)) {
        redeemReservation();
      } else {
        throw new BookIsReservedException();
      }
    }
    this.currentBookIssue =
        BookIssue.createIssue(userId, this.clock.instant().plus(Duration.ofDays(14)));
    return this.currentBookIssue;
  }

  public void returnBook(final UserId userId) {
    if (!isIssued()) {
      throw new BookNotIssuedException();
    } else if (!isIssuedTo(userId)) {
      throw new BookCannotBeReturnedByUserException();
    } else {
      // Raise domain event ?
      this.currentBookIssue = null;
    }
  }

  private boolean isIssuedTo(final UserId userId) {
    return isIssued() && this.currentBookIssue.userId().equals(userId);
  }

  public BookReservation reserveToUser(final UserId userId) {
    if (!isIssued()) {
      throw new BookNotIssuedException();
    } else if (isIssuedTo(userId)) {
      throw new BookAlreadyIssuedToUserException();
    } else {
      if (this.currentReservation != null && !this.currentReservation.isReservedBy(userId)) {
        throw new BookIsReservedException();
      }
      this.currentReservation = BookReservation.create(this.clock, userId);
    }
    return this.currentReservation;
  }

  public void extendIssue(final UserId userId) {
    if (!isIssued()) {
      throw new BookNotIssuedException();
    } else if (isReserved()) {
      throw new BookCannotBeExtendedByUserException();
    }
    this.currentBookIssue.renew(this.clock);
  }

  public void expireReservation() {
    // TODO: Create Event ?
    this.currentReservation = null;
  }

  private void redeemReservation() {
    // TODO: Create Event ?
    this.currentReservation = null;
  }

  private boolean isIssued() {
    return this.currentBookIssue != null;
  }

  private boolean isReserved() {
    return this.currentReservation != null;
  }
}
