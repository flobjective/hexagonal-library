package com.ffr.booklibrary.core.circulation.core.ports.outgoing;

import com.ffr.booklibrary.core.circulation.core.model.Book;
import com.ffr.booklibrary.core.circulation.core.model.BookId;
import com.ffr.booklibrary.core.circulation.core.model.UserId;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> find(final BookId bookId);

    Book save(Book book);

    List<Book> listAvailableBooks();

    List<Book> listIssuedBooks(UserId userId);
}
