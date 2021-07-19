package com.ffr.booklibrary.inventory.core.ports.incoming;

import com.ffr.booklibrary.inventory.core.model.Book;

import java.util.List;

public interface ListBooks {
    List<Book> listBooks();
}
