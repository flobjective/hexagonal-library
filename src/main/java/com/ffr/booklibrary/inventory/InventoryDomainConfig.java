package com.ffr.booklibrary.inventory;

import com.ffr.booklibrary.inventory.core.adapters.db.*;
import com.ffr.booklibrary.inventory.core.adapters.event.BookEventPublisherMicronautAdapter;
import com.ffr.booklibrary.inventory.core.adapters.openlibrary.OpenLibraryApi;
import com.ffr.booklibrary.inventory.core.application.ports.incoming.ListBooks;
import com.ffr.booklibrary.inventory.core.application.ports.incoming.RegisterBook;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookDetailsProvider;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookEventPublisher;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.Books;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.EventsRepository;
import com.ffr.booklibrary.inventory.core.application.services.BookService;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.event.ApplicationEventPublisher;
import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class InventoryDomainConfig {

  @Inject private ApplicationEventPublisher eventPublisher;

  @Inject private BookJpaRepository bookJpaRepository;

  @Inject private EventsJpaRepository eventsJpaRepository;

  @Inject private OpenLibraryApi openLibraryApi;

  //  @Inject private KafkaEventSender kafkaEventSender;

  @Singleton
  Books bookRepository() {
    return new BooksJpaAdapter(bookJpaRepository);
  }

  @Singleton
  EventsRepository eventsRepository() {
    return new EventsJpaRepositoryAdapter(eventsJpaRepository);
  }

  @Singleton
  BookDetailsProvider bookDetailsProvider() {
    return openLibraryApi;
  }

  @Singleton
  BookEventPublisher bookEventPublisher() {
    return new BookEventPublisherMicronautAdapter(this.eventPublisher);
    //    System.out.println(this.kafkaEventSender);
    //    return new BookEventPublisherTransactionalOutboxAdapter(
    //        this.eventsRepository(), this.kafkaEventSender);
  }

  @Singleton
  @Inject
  BookService bookService(
      final Books books,
      final BookDetailsProvider bookDetailsProvider,
      final BookEventPublisher bookEventPublisher) {
    return new BookService(bookDetailsProvider, books, bookEventPublisher);
  }

  @Singleton
  public RegisterBook addBook(final BookService bookService) {
    return bookService;
  }

  @Singleton
  @Inject
  public ListBooks listBooks(final BookService bookService) {
    return bookService;
  }
}
