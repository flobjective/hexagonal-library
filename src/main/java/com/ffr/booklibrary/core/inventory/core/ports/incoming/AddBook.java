package com.ffr.booklibrary.core.inventory.core.ports.incoming;

import com.ffr.booklibrary.core.inventory.core.model.AddBookCommand;
import com.ffr.booklibrary.core.inventory.core.model.Book;

import java.util.Optional;

public interface AddBook {
    Optional<Book> addBook(AddBookCommand command);
}
