package com.ffr.booklibrary.inventory.core.application.services;

import com.ffr.booklibrary.inventory.core.application.ports.incoming.ListBooks;
import com.ffr.booklibrary.inventory.core.application.ports.incoming.RegisterBook;
import com.ffr.booklibrary.inventory.core.application.ports.incoming.RegisterBookCommand;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookDetailsProvider;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookEventPublisher;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookRepository;
import com.ffr.booklibrary.inventory.core.domain.model.Book;
import com.ffr.booklibrary.shared.events.BookAddedEvent;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

public class BookService implements RegisterBook, ListBooks {

  private BookDetailsProvider bookDetailsProvider;
  private BookRepository bookRepository;
  private BookEventPublisher bookEventPublisher;

  public BookService(
      final BookDetailsProvider bookDetailsProvider,
      final BookRepository bookRepository,
      final BookEventPublisher bookEventPublisher) {
    this.bookDetailsProvider = bookDetailsProvider;
    this.bookRepository = bookRepository;
    this.bookEventPublisher = bookEventPublisher;
  }

  @Transactional
  @Override
  public Optional<Book> registerBook(final RegisterBookCommand command) {
    var book = this.bookDetailsProvider.find(command.isbn());
    var savedBook = book.map(Book::cloneBook).map(b -> this.bookRepository.save(b));
    savedBook.ifPresent(
        presentBook ->
            this.bookEventPublisher.publishEvents(
                List.of(
                    BookAddedEvent.create(
                        presentBook.id(), presentBook.inventoryNumber().toString()))));
    return savedBook;
  }

  @Override
  public List<Book> listBooks() {
    return this.bookRepository.list();
  }
}
