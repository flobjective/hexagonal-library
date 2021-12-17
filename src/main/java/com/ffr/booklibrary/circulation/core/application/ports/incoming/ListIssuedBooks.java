package com.ffr.booklibrary.circulation.core.application.ports.incoming;

import com.ffr.booklibrary.circulation.core.domain.model.Book;
import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import java.util.List;

public interface ListIssuedBooks {

  List<Book> listIssuedBooks(UserId userId);
}
