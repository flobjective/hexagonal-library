package com.ffr.booklibrary.circulation.core.adapters.db;

import com.ffr.booklibrary.circulation.core.application.ports.outgoing.BookRepository;
import com.ffr.booklibrary.circulation.core.domain.model.*;
import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Singleton;

@Singleton
public class BookJpaRepositoryAdapter implements BookRepository {

  private BookJpaRepository bookJpaRepository;

  public BookJpaRepositoryAdapter(final BookJpaRepository bookJpaRepository) {
    this.bookJpaRepository = bookJpaRepository;
  }

  @Override
  public Optional<Book> find(final BookId book) {
    return bookJpaRepository.findById(book.id()).map(JpaBook::toBook);
  }

  @Override
  public Book insert(Book book) {
    return bookJpaRepository.save(JpaBook.from(book)).toBook();
  }

  @Override
  public Book save(final Book book) {
    var existing = bookJpaRepository.findById(book.id()).orElseThrow();
    var jpaBook = JpaBook.from(book);
    existing.setCurrentIssue(jpaBook.getCurrentIssue());
    existing.setCurrentReservation(jpaBook.getCurrentReservation());
    return bookJpaRepository.update(existing).toBook();
  }

  @Override
  public List<BookReadModel> listIssuedBooks(final UserId userId) {
    return this.bookJpaRepository.findIssuedByUserId(userId.id()).stream()
        .map(JpaBook::toReadModel)
        .collect(Collectors.toList());
  }

  @Override
  public List<Book> findBooksWithExpiredReservations(final Clock clock) {
    return bookJpaRepository.findBooksWithExpiredReservations(clock.instant());
  }

  @Override
  public List<AvailableBookReadModel> listAvailableBooks() {
    return this.bookJpaRepository.findAvailable().stream()
        .map(JpaBook::toAvailableReadModel)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<AvailableBookReadModel> readAvailableBook(BookId bookId) {
    return this.bookJpaRepository.findByBookId(bookId.id()).map(JpaBook::toAvailableReadModel);
  }
}
