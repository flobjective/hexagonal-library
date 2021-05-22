package com.ffr.booklibrary.core.circulation.core.ports.incoming;

import com.ffr.booklibrary.core.circulation.core.model.Book;

import java.util.List;

public interface ListAvailableBooks {

    List<Book> listAvailableBooks();
}
