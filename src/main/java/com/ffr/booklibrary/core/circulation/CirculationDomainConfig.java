package com.ffr.booklibrary.core.circulation;

import com.ffr.booklibrary.core.circulation.adapters.db.BookJpaRepository;
import com.ffr.booklibrary.core.circulation.adapters.db.BookJpaRepositoryAdapter;
import com.ffr.booklibrary.core.circulation.adapters.event.AddBookEventAdapter;
import com.ffr.booklibrary.core.circulation.core.CirculationService;
import com.ffr.booklibrary.core.circulation.core.ports.outgoing.BookRepository;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.event.ApplicationEventPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class CirculationDomainConfig {

    @Inject
    ApplicationEventPublisher eventPublisher;

    @Inject
    BookJpaRepository bookJpaRepository;

    @Singleton
    BookRepository bookRepository() {
        return new BookJpaRepositoryAdapter(bookJpaRepository);
    }

    @Singleton
    @Inject
    CirculationService circulationService(final BookRepository bookRepository) {
        return new CirculationService(bookRepository);
    }

    @Singleton
    @Inject
    AddBookEventAdapter addBookEventAdapter(final CirculationService addBookToCirculation) {
        return new AddBookEventAdapter(addBookToCirculation);
    }
}
