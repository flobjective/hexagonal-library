package com.ffr.booklibrary.inventory.core.application.ports.outgoing;

import com.ffr.booklibrary.inventory.core.domain.model.Book;
import java.util.List;

public interface Books {
  Book save(final Book book);

  List<Book> all();
}
