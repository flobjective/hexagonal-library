package com.ffr.booklibrary.core.circulation.core.ports.incoming;

import com.ffr.booklibrary.core.circulation.core.model.BookId;
import com.ffr.booklibrary.core.circulation.core.model.BookIssue;

public interface ReturnBook {

    void returnBook(BookId bookId, BookIssue bookIssue);
}
