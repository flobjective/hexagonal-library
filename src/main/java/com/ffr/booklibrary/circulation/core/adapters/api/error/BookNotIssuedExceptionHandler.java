package com.ffr.booklibrary.circulation.core.adapters.api.error;

import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookNotIssuedException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {BookNotIssuedException.class, ExceptionHandler.class})
public class BookNotIssuedExceptionHandler implements ExceptionHandler<BookNotIssuedException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, BookNotIssuedException exception) {
        return HttpResponse.badRequest();
    }
}
