package com.ffr.booklibrary.circulation.core.application.services;

import com.ffr.booklibrary.circulation.core.application.ports.incoming.*;
import com.ffr.booklibrary.circulation.core.application.ports.outgoing.BookRepository;
import com.ffr.booklibrary.circulation.core.application.ports.outgoing.UserRepository;
import com.ffr.booklibrary.circulation.core.domain.model.*;
import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookNotFoundException;
import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.scheduling.annotation.Scheduled;
import java.time.Clock;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CirculationService
    implements AddBookToCirculation, IssueBook, ReturnBook, ListIssuedBooks, ListAvailableBooks {

  private final Clock clock;
  private final BookRepository bookRepository;
  private final UserRepository userRepository;
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  @Transactional
  public void issueBook(final IssueBookCommand issueBookCommand) {
    Book book =
        this.bookRepository
            .find(issueBookCommand.getBookId())
            .orElseThrow(() -> new BookNotFoundException(issueBookCommand.getBookId()));
    var result = book.issueToUser(issueBookCommand.getUserId());
    this.bookRepository.save(book);
    //        this.applicationEventPublisher.publishEvent(result.event());
  }

  @Override
  @Transactional
  public void returnBook(final ReturnBookCommand returnBookCommand) {
    Book book =
        this.bookRepository
            .find(returnBookCommand.bookId())
            .orElseThrow(() -> new BookNotFoundException(returnBookCommand.bookId()));
    book.returnBook(returnBookCommand.userId());
    this.bookRepository.save(book);
  }

  @Override
  public List<BookReadModel> listIssuedBooks(final UserId userId) {
    return this.bookRepository.listIssuedBooks(userId);
  }

  @Override
  @Transactional
  public void addBookToCirculation(final InventoryNumber inventoryNumber) {
    Book newBook = Book.create(this.clock, inventoryNumber);
    this.bookRepository.insert(newBook);
  }

  @Scheduled(initialDelay = "20s", fixedDelay = "10s")
  private void expireReservations() {
    var books = this.bookRepository.findBooksWithExpiredReservations(this.clock);
    books.stream()
        .filter(book -> book.currentReservation().hasExpired(this.clock))
        .forEach(Book::expireReservation);
  }

  @Override
  public List<AvailableBookReadModel> listAvailableBooks() {
    return this.bookRepository.listAvailableBooks();
  }
}
