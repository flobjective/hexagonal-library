package com.ffr.booklibrary.core.inventory;

import com.ffr.booklibrary.core.inventory.core.model.Book;
import com.ffr.booklibrary.core.inventory.core.ports.outgoing.BookDetailsProvider;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FakeBookDetailsProvider implements BookDetailsProvider {

    private HashMap<String, Book> books = new HashMap<>();

    public FakeBookDetailsProvider(final Collection<Book> books) {
        this.books.putAll(books.stream().collect(Collectors.toMap((Book b) -> b.bookIdentification().isbn13().value(),
                Function.identity())));
    }

    @Override
    public Optional<Book> find(final String isbn) {
        return Optional.ofNullable(this.books.get(isbn));
    }
}
