package com.ffr.booklibrary.circulation.core.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookAlreadyIssuedException;
import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookAlreadyIssuedToUserException;
import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookCannotBeReturnedByUserException;
import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookNotIssuedException;
import java.time.Clock;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class BookTest {

  private static Book createBook() {
    return Book.create(Clock.systemUTC(), new InventoryNumber(UUID.randomUUID().toString()));
  }

  @Test
  void issueToUser_works() {
    Book book = createBook();
    UserId userId = new UserId(UUID.randomUUID());
    BookIssue issue = book.issueToUser(userId);

    assertNotNull(issue);
  }

  @Test
  void issueToUser_fails_already_issued() {
    Book book = createBook();
    UserId userId = new UserId(UUID.randomUUID());
    book.issueToUser(userId);

    UserId anotherUserId = new UserId(UUID.randomUUID());
    assertThrows(BookAlreadyIssuedException.class, () -> book.issueToUser(anotherUserId));
  }

  @Test
  void issueToUser_fails_reserved() {
    Book book = createBook();
    UserId firstReader = new UserId(UUID.randomUUID());
    UserId secondReader = new UserId(UUID.randomUUID());
    book.issueToUser(firstReader);
    book.reserveToUser(secondReader);
    book.returnBook(firstReader);
    // Book is now available again

    UserId anotherUserId = new UserId(UUID.randomUUID());
    assertThrows(BookIsReservedException.class, () -> book.issueToUser(anotherUserId));
  }

  @Test
  void extendIssue_works() {
    Book book = createBook();
    UserId userId = new UserId(UUID.randomUUID());
    BookIssue issue = book.issueToUser(userId);
    book.extendIssue(userId);

    assertNotNull(issue);
  }

  @Test
  void returnBook_works() {
    Book book = createBook();
    UserId userId = new UserId(UUID.randomUUID());
    book.issueToUser(userId);
    book.returnBook(userId);

    assertNull(book.currentBookIssue());
  }

  @Test
  void returnBook_fails_wrong_user() {
    Book book = createBook();
    UserId userId = new UserId(UUID.randomUUID());
    book.issueToUser(userId);

    assertThrows(
        BookCannotBeReturnedByUserException.class,
        () -> book.returnBook(new UserId(UUID.randomUUID())));
  }

  @Test
  void reserveToUser_works() {
    Book book = createBook();
    UserId userId = new UserId(UUID.randomUUID());
    book.issueToUser(userId);

    var anotherUser = new UserId(UUID.randomUUID());
    var reservation = book.reserveToUser(anotherUser);

    assertNotNull(reservation);
    assertEquals(anotherUser, reservation.userId());
  }

  @Test
  void reserveToUser_fails_not_issued() {
    Book book = createBook();
    UserId userId = new UserId(UUID.randomUUID());

    assertThrows(BookNotIssuedException.class, () -> book.reserveToUser(userId));
  }

  @Test
  void reserveToUser_fails_already_reserved() {
    Book book = createBook();
    UserId userId = new UserId(UUID.randomUUID());
    book.issueToUser(userId);
    book.reserveToUser(new UserId(UUID.randomUUID()));

    assertThrows(
        BookIsReservedException.class, () -> book.reserveToUser(new UserId(UUID.randomUUID())));
  }

  @Test
  void reserveToUser_fails_already_issued() {
    Book book = createBook();
    UserId userId = new UserId(UUID.randomUUID());
    book.issueToUser(userId);

    assertThrows(BookAlreadyIssuedToUserException.class, () -> book.reserveToUser(userId));
  }
}
