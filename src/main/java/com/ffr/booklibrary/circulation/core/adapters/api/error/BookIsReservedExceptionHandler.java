package com.ffr.booklibrary.circulation.core.adapters.api.error;

import com.ffr.booklibrary.circulation.core.domain.model.BookIsReservedException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {BookIsReservedException.class, ExceptionHandler.class})
public class BookIsReservedExceptionHandler
    implements ExceptionHandler<BookIsReservedException, HttpResponse> {

  @Override
  public HttpResponse handle(HttpRequest request, BookIsReservedException exception) {
    return HttpResponse.badRequest(exception.getMessage());
  }
}
