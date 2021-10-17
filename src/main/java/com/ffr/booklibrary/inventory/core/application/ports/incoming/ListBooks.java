package com.ffr.booklibrary.inventory.core.application.ports.incoming;

import com.ffr.booklibrary.inventory.core.domain.model.Book;

import java.util.List;

public interface ListBooks {
    List<Book> listBooks();
}
