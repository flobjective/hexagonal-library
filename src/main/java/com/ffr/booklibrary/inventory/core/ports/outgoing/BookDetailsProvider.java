package com.ffr.booklibrary.inventory.core.ports.outgoing;

import com.ffr.booklibrary.inventory.core.model.Book;

import java.util.Optional;

public interface BookDetailsProvider {
    Optional<Book> find(String isbn);
}
