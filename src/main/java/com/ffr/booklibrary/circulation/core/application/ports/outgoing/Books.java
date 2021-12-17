package com.ffr.booklibrary.circulation.core.application.ports.outgoing;

import com.ffr.booklibrary.circulation.core.domain.model.*;
import java.time.Clock;
import java.util.List;
import java.util.Optional;

public interface Books {
  Optional<Book> withId(final BookId bookId);

  Book insert(Book book);

  Book save(Book book);

  List<BookReadModel> issuedTo(UserId userId);

  List<Book> withExpiredReservations(Clock clock);

  List<AvailableBookReadModel> available();

  Optional<AvailableBookReadModel> readAvailableBook(BookId bookId);
}
