package com.ffr.booklibrary.circulation.core.adapters.api.error;

import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookNotFoundException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {BookNotFoundException.class, ExceptionHandler.class})
public class BookNotFoundExceptionHandler
    implements ExceptionHandler<BookNotFoundException, HttpResponse> {

  @Override
  public HttpResponse handle(HttpRequest request, BookNotFoundException exception) {
    return HttpResponse.notFound(exception.getMessage());
  }
}
