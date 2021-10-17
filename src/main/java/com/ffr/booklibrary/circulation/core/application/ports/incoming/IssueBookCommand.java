package com.ffr.booklibrary.circulation.core.application.ports.incoming;

import com.ffr.booklibrary.circulation.core.domain.model.BookId;
import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IssueBookCommand {

    private final BookId bookId;
    private final UserId userId;

    public BookId getBookId() {
        return bookId;
    }

    public UserId getUserId() {
        return userId;
    }
}
