package com.ffr.booklibrary.circulation.core.application.ports.incoming;

import com.ffr.booklibrary.circulation.core.domain.model.AvailableBookReadModel;
import com.ffr.booklibrary.circulation.core.domain.model.BookId;

public interface GetAvailableBook {

    AvailableBookReadModel getAvailableBook(BookId bookId);
}
