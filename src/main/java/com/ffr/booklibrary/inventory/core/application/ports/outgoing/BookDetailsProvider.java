package com.ffr.booklibrary.inventory.core.application.ports.outgoing;

import com.ffr.booklibrary.inventory.core.domain.model.Book;
import java.util.Optional;

public interface BookDetailsProvider {
  Optional<Book> find(String isbn);
}
