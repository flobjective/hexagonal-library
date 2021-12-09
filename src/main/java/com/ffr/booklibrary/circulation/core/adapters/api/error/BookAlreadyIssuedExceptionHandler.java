package com.ffr.booklibrary.circulation.core.adapters.api.error;

import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookAlreadyIssuedException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {BookAlreadyIssuedException.class, ExceptionHandler.class})
public class BookAlreadyIssuedExceptionHandler
    implements ExceptionHandler<BookAlreadyIssuedException, HttpResponse> {

  @Override
  public HttpResponse handle(HttpRequest request, BookAlreadyIssuedException exception) {
    return HttpResponse.badRequest(exception.getMessage());
  }
}
