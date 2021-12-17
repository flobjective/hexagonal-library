package com.ffr.booklibrary.inventory.core.adapters.db;

import com.ffr.booklibrary.inventory.core.application.ports.outgoing.Books;
import com.ffr.booklibrary.inventory.core.domain.model.Book;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.inject.Singleton;

@Singleton
public class BooksJpaAdapter implements Books {

  private BookJpaRepository bookJpaRepository;

  public BooksJpaAdapter(final BookJpaRepository bookJpaRepository) {
    this.bookJpaRepository = bookJpaRepository;
  }

  @Override
  public Book save(final Book book) {
    return JpaBook.toBook(this.bookJpaRepository.save(JpaBook.of(book)));
  }

  @Override
  public List<Book> all() {
    return StreamSupport.stream(this.bookJpaRepository.findAll().spliterator(), false)
        .map(JpaBook::toBook)
        .collect(Collectors.toList());
  }
}
