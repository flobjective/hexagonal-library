package com.ffr.booklibrary.inventory.core.adapters.openlibrary;

import com.ffr.booklibrary.inventory.core.domain.model.Book;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookDetailsProvider;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class OpenLibraryApi implements BookDetailsProvider {

    @Client("https://openlibrary.org/api")
    @Inject
    private RxHttpClient rxHttpClient;

    @Override
    public Optional<Book> find(final String isbn) {
        var url = UriBuilder.of("/books")
                .queryParam("bibkeys", String.format("ISBN:%s", isbn))
                .queryParam("jscmd", "data")
                .queryParam("format", "json").build().toString();
        var result = rxHttpClient
                .toBlocking()
                .retrieve(HttpRequest.GET(url), Argument.mapOf(String.class, OLBook.class));
        return result.values().stream().findFirst().map(OLBook::toBook);
    }
}
