package com.ffr.booklibrary.circulation.config;

import com.ffr.booklibrary.circulation.core.application.ports.outgoing.Books;
import com.ffr.booklibrary.circulation.core.application.ports.outgoing.UserRepository;
import com.ffr.booklibrary.circulation.core.application.services.CirculationService;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.event.ApplicationEventPublisher;
import java.time.Clock;
import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class CirculationDomainConfig {

  @Singleton
  @Inject
  CirculationService circulationService(
      final Books books,
      final UserRepository userRepository,
      final ApplicationEventPublisher applicationEventPublisher) {
    return new CirculationService(Clock.systemUTC(), books, userRepository);
  }
}
