package com.ffr.booklibrary.circulation.core.application.services;

import com.ffr.booklibrary.circulation.core.application.ports.incoming.*;
import com.ffr.booklibrary.circulation.core.application.ports.outgoing.Books;
import com.ffr.booklibrary.circulation.core.application.ports.outgoing.UserRepository;
import com.ffr.booklibrary.circulation.core.domain.model.*;
import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookNotFoundException;
import io.micronaut.context.event.ApplicationEventPublisher;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.time.Clock;
import java.util.List;

@AllArgsConstructor
public class CirculationService
    implements RegisterBookToCirculation,
        IssueBook,
        ReturnBook,
        ListIssuedBooks,
        ListAvailableBooks,
        ReserveBook,
        GetAvailableBook {

  private final Clock clock;
  private final Books allBooks;
  private final UserRepository userRepository;
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  @Transactional
  public void issueBook(final IssueBookCommand issueBookCommand) {
    Book book =
        this.allBooks
            .withId(issueBookCommand.getBookId())
            .orElseThrow(() -> new BookNotFoundException(issueBookCommand.getBookId()));
    var result = book.issueToUser(issueBookCommand.getUserId());
    this.allBooks.save(book);
    //        this.applicationEventPublisher.publishEvent(result.event());
  }

  @Override
  @Transactional
  public void returnBook(final ReturnBookCommand returnBookCommand) {
    Book book =
        this.allBooks
            .withId(returnBookCommand.bookId())
            .orElseThrow(() -> new BookNotFoundException(returnBookCommand.bookId()));
    book.returnBook(returnBookCommand.userId());
    this.allBooks.save(book);
  }

  @Override
  public List<BookReadModel> listIssuedBooks(final UserId userId) {
    return this.allBooks.issuedTo(userId);
  }

  @Override
  @Transactional
  public void addBookToCirculation(final InventoryNumber inventoryNumber) {
    Book newBook = Book.create(this.clock, inventoryNumber);
    this.allBooks.insert(newBook);
  }

  public void expireReservations() {
    var books = this.allBooks.withExpiredReservations(this.clock);
    books.stream()
        .filter(book -> book.currentReservation().hasExpired(this.clock))
        .forEach(Book::expireReservation);
  }

  @Override
  public List<AvailableBookReadModel> listAvailableBooks() {
    return this.allBooks.available();
  }

  @Override
  public void reserveBook(final ReserveBookCommand reserveBookCommand) {
    Book book =
        this.allBooks
            .withId(reserveBookCommand.bookId())
            .orElseThrow(() -> new BookNotFoundException(reserveBookCommand.bookId()));
    book.reserveToUser(reserveBookCommand.userId());
    this.allBooks.save(book);
  }

  @Override
  public AvailableBookReadModel getAvailableBook(final BookId bookId) {
    return this.allBooks
            .readAvailableBook(bookId)
            .orElseThrow(() -> new BookNotFoundException(bookId));
  }
}
