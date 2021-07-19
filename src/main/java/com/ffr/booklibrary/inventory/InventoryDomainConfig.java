package com.ffr.booklibrary.inventory;

import com.ffr.booklibrary.inventory.adapters.db.BookJpaRepository;
import com.ffr.booklibrary.inventory.adapters.db.BookJpaRepositoryAdapter;
import com.ffr.booklibrary.inventory.adapters.event.BookEventPublisherMicronautAdapter;
import com.ffr.booklibrary.inventory.adapters.openlibrary.OpenLibraryApi;
import com.ffr.booklibrary.inventory.core.BookService;
import com.ffr.booklibrary.inventory.core.ports.incoming.AddBook;
import com.ffr.booklibrary.inventory.core.ports.incoming.ListBooks;
import com.ffr.booklibrary.inventory.core.ports.outgoing.BookDetailsProvider;
import com.ffr.booklibrary.inventory.core.ports.outgoing.BookEventPublisher;
import com.ffr.booklibrary.inventory.core.ports.outgoing.BookRepository;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.event.ApplicationEventPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class InventoryDomainConfig {

    @Inject
    private ApplicationEventPublisher eventPublisher;

    @Inject
    private BookJpaRepository bookJpaRepository;

    @Inject
    private OpenLibraryApi openLibraryApi;

    @Singleton
    BookRepository bookRepository() {
        return new BookJpaRepositoryAdapter(bookJpaRepository);
    }

    @Singleton
    BookDetailsProvider bookDetailsProvider() {
        return openLibraryApi;
    }

    @Singleton
    BookEventPublisher bookEventPublisher() {
        return new BookEventPublisherMicronautAdapter(this.eventPublisher);
    }

    @Singleton
    @Inject
    BookService bookService(final BookRepository bookRepository, final BookDetailsProvider bookDetailsProvider,
                            final BookEventPublisher bookEventPublisher) {
        return new BookService(bookDetailsProvider, bookRepository, bookEventPublisher);
    }

    @Singleton
    public AddBook addBook(final BookService bookService) {
        return bookService;
    }

    @Singleton
    @Inject
    public ListBooks listBooks(final BookService bookService) {
        return bookService;
    }

}
