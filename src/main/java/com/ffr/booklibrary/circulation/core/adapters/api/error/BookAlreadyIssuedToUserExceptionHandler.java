package com.ffr.booklibrary.circulation.core.adapters.api.error;

import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookAlreadyIssuedToUserException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {BookAlreadyIssuedToUserException.class, ExceptionHandler.class})
public class BookAlreadyIssuedToUserExceptionHandler
    implements ExceptionHandler<BookAlreadyIssuedToUserException, HttpResponse> {

  @Override
  public HttpResponse handle(HttpRequest request, BookAlreadyIssuedToUserException exception) {
    return HttpResponse.badRequest(exception.getMessage());
  }
}
