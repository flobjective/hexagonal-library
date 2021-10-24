package com.ffr.booklibrary.circulation.core.application.ports.outgoing;

import com.ffr.booklibrary.circulation.core.domain.model.Book;
import com.ffr.booklibrary.circulation.core.domain.model.BookId;
import com.ffr.booklibrary.circulation.core.domain.model.BookReadModel;
import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
  Optional<Book> find(final BookId bookId);

  Book insert(Book book);

  Book save(Book book);

  List<BookReadModel> listIssuedBooks(UserId userId);
}
