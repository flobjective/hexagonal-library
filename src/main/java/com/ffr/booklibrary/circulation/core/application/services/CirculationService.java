package com.ffr.booklibrary.circulation.core.application.services;

import com.ffr.booklibrary.circulation.core.application.ports.incoming.*;
import com.ffr.booklibrary.circulation.core.application.ports.outgoing.Books;
import com.ffr.booklibrary.circulation.core.application.ports.outgoing.UserRepository;
import com.ffr.booklibrary.circulation.core.domain.model.*;
import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookNotFoundException;
import java.time.Clock;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

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

  @Override
  @Transactional
  public void issueBook(final IssueBookCommand issueBookCommand) {
    Book book =
        this.allBooks
            .withId(issueBookCommand.getBookId())
            .orElseThrow(() -> new BookNotFoundException(issueBookCommand.getBookId()));
    var result = book.issueToUser(issueBookCommand.getUserId());
    this.allBooks.save(book);
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
  public List<Book> listIssuedBooks(final UserId userId) {
    return this.allBooks.issuedTo(userId);
  }

  @Override
  @Transactional
  public void addBookToCirculation(final InventoryNumber inventoryNumber) {
    Book newBook = Book.create(this.clock, inventoryNumber);
    this.allBooks.insert(newBook);
  }

  //  public void expireReservations() {
  //    var books = this.allBooks.withExpiredReservations(this.clock);
  //    books.stream()
  //        .filter(book -> book.currentReservation().hasExpired(this.clock))
  //        .forEach(Book::expireReservation);
  //  }

  @Override
  public List<Book> listAvailableBooks() {
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
  public Book getAvailableBook(final BookId bookId) {
    return this.allBooks.withId(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
  }
}
