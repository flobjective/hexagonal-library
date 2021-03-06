package com.ffr.booklibrary.inventory.core.application.ports.incoming;

import com.ffr.booklibrary.inventory.core.domain.model.Book;
import java.util.Optional;

public interface RegisterBook {
  Optional<Book> registerBook(RegisterBookCommand command);
}
