package com.ffr.booklibrary.circulation.core.model;

import com.ffr.booklibrary.circulation.core.model.exceptions.BookAlreadyIssuedException;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private static Book createBook() {
        return Book.create(new InventoryNumber(UUID.randomUUID().toString()));
    }

    @Test
    void issueToUser_works() {
        Book book = createBook();
        UserId userId = new UserId(UUID.randomUUID());
        BookIssue issue = book.issueToUser(userId, Clock.systemUTC());

        assertNotNull(issue);
    }

    @Test
    void issueToUser_fails_already_issued() {
        Clock systemUTC = Clock.systemUTC();
        Book book = createBook();
        UserId userId = new UserId(UUID.randomUUID());
        book.issueToUser(userId, systemUTC);

        UserId anotherUserId = new UserId(UUID.randomUUID());
        assertThrows(BookAlreadyIssuedException.class, () -> book.issueToUser(anotherUserId, systemUTC));
    }

    @Test
    void returnBook_works() {
        Book book = createBook();
        UserId userId = new UserId(UUID.randomUUID());
        BookIssue issue = book.issueToUser(userId, Clock.systemUTC());
        book.returnBook();

        assertNull(book.currentBookIssue());
    }
}