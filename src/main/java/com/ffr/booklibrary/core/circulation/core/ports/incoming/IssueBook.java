package com.ffr.booklibrary.core.circulation.core.ports.incoming;

import com.ffr.booklibrary.core.circulation.core.model.BookId;
import com.ffr.booklibrary.core.circulation.core.model.UserId;

public interface IssueBook {

    void issueBook(final BookId bookId, final UserId userId);
}
