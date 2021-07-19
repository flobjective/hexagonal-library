package com.ffr.booklibrary.circulation.config;

import com.ffr.booklibrary.circulation.core.ports.outgoing.BookRepository;
import com.ffr.booklibrary.circulation.core.service.CirculationService;
import io.micronaut.context.annotation.Factory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Clock;

@Factory
public class CirculationDomainConfig {

    @Singleton
    @Inject
    CirculationService circulationService(final BookRepository bookRepository) {
        return new CirculationService(Clock.systemUTC(), bookRepository);
    }
}
