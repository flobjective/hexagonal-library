package com.ffr.booklibrary.inventory.core.ports.outgoing;

import com.ffr.booklibrary.inventory.core.model.Book;

import java.util.List;

public interface BookRepository {
    Book save(final Book book);

    List<Book> list();
}
