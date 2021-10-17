package com.ffr.booklibrary.circulation.config;

import com.ffr.booklibrary.circulation.core.application.ports.outgoing.BookRepository;
import com.ffr.booklibrary.circulation.core.application.services.CirculationService;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.event.ApplicationEventPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Clock;

@Factory
public class CirculationDomainConfig {

  @Singleton
  @Inject
  CirculationService circulationService(
      final BookRepository bookRepository,
      final ApplicationEventPublisher applicationEventPublisher) {
    return new CirculationService(Clock.systemUTC(), bookRepository, applicationEventPublisher);
  }
}
