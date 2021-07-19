package com.ffr.booklibrary.circulation.core.ports.incoming;

import com.ffr.booklibrary.circulation.core.model.BookReadModel;
import com.ffr.booklibrary.circulation.core.model.UserId;

import java.util.List;

public interface ListIssuedBooks {

    List<BookReadModel> listIssuedBooks(UserId userId);

}
