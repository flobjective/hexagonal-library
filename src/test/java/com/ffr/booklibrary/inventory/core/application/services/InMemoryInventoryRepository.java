package com.ffr.booklibrary.inventory.core.application.services;

import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookRepository;
import com.ffr.booklibrary.inventory.core.domain.model.Book;
import com.ffr.booklibrary.inventory.core.domain.model.BookIdentification;
import com.ffr.booklibrary.inventory.core.domain.model.BookTitle;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryInventoryRepository implements BookRepository {

    private final AtomicLong counter = new AtomicLong(0);
    private final ConcurrentHashMap<Long, Book> books = new ConcurrentHashMap<>();

    public InMemoryInventoryRepository(final Collection<Book> books) {
        books.stream().forEach(this::save);
    }

    @Override
    public Book save(final Book book) {
        var id = counter.getAndIncrement();
        books.put(id, Book.builder().title(new BookTitle(book.title().title()))
                .bookIdentification(BookIdentification.builder()
                        .isbn10(book.bookIdentification().isbn10())
                        .isbn13(book.bookIdentification().isbn13()).build()).build());
        return books.get(id);
    }

    @Override
    public List<Book> list() {
        return books.values().stream().collect(Collectors.toList());
    }

    public Book getBookById(final Long id) {
        return this.books.get(id);
    }
}