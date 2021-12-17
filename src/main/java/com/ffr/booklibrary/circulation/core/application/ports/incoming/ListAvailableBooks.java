package com.ffr.booklibrary.circulation.core.application.ports.incoming;

import com.ffr.booklibrary.circulation.core.domain.model.Book;
import java.util.List;

public interface ListAvailableBooks {

  List<Book> listAvailableBooks();
}
