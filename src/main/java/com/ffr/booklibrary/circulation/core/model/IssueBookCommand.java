package com.ffr.booklibrary.circulation.core.model;

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
