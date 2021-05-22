package com.ffr.booklibrary.core.circulation.core.ports.incoming;

import com.ffr.booklibrary.core.circulation.core.model.Book;
import com.ffr.booklibrary.core.circulation.core.model.UserId;

import java.util.List;

public interface ListIssuedBooks {

    List<Book> listIssuedBooks(UserId userId);

}
