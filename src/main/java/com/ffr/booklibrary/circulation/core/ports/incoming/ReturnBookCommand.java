package com.ffr.booklibrary.circulation.core.ports.incoming;

import com.ffr.booklibrary.circulation.core.model.BookId;
import com.ffr.booklibrary.circulation.core.model.BookIssue;

public class ReturnBookCommand {
    private final BookId bookId;
    private final BookIssue bookIssue;

    public ReturnBookCommand(BookId bookId, BookIssue bookIssue) {
        this.bookId = bookId;
        this.bookIssue = bookIssue;
    }

    public BookId getBookId() {
        return bookId;
    }

    public BookIssue getBookIssue() {
        return bookIssue;
    }
}
