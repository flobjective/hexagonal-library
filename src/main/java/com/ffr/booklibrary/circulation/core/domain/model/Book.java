package com.ffr.booklibrary.circulation.core.domain.model;

import com.ffr.booklibrary.circulation.core.domain.model.exceptions.*;
import com.ffr.booklibrary.shared.events.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.Clock;
import java.time.Duration;

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
      if (!this.currentReservation.isActive(this.clock)) {
        expireReservation();
      } else if (this.currentReservation.userId().equals(userId)) {
        redeemReservation();
      } else {
        throw new BookIsReservedException();
      }
    }
    this.currentBookIssue =
        BookIssue.createIssue(userId, clock.instant().plus(Duration.ofDays(14)));
    return this.currentBookIssue;
  }

  public void returnBook(final UserId userId) {
    if (!isIssued()) {
      throw new BookNotIssuedException();
    } else if (!this.currentBookIssue.userId().equals(userId)) {
      throw new BookCannotBeReturnedByUserException();
    } else {
      this.currentBookIssue = null;
    }
  }

  public BookReservation reserveToUser(final UserId userId) {
    if (!isIssued()) {
      throw new BookNotIssuedException();
    } else if (this.currentBookIssue.userId().equals(userId)) {
      throw new BookAlreadyIssuedToUserException();
    } else {
      this.currentReservation = BookReservation.create(this.clock, userId);
    }
    return this.currentReservation;
  }

  public void extendIssue(final UserId userId) {
    if (!isIssued()) {
      throw new BookNotIssuedException();
    } else if (!this.currentBookIssue.userId().equals(userId)) {
      throw new BookCannotBeExtendedByUserException();
    }
    this.currentBookIssue.renew(this.clock);
  }

  private void expireReservation() {
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

  @AllArgsConstructor
  public static class BookIssuedEvent implements DomainEvent {
    private BookId bookId;
    private UserId userId;
  }
}
