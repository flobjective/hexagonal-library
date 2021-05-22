package com.ffr.booklibrary.core.inventory.core.ports.outgoing;

import com.ffr.booklibrary.core.inventory.core.model.Book;

import java.util.List;

public interface BookRepository {
    Book save(final Book book);

    List<Book> list();
}
