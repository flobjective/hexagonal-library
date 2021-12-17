package com.ffr.booklibrary.circulation.core.application.ports.incoming;

import com.ffr.booklibrary.circulation.core.domain.model.Book;
import com.ffr.booklibrary.circulation.core.domain.model.BookId;

public interface GetAvailableBook {

  Book getAvailableBook(BookId bookId);
}
