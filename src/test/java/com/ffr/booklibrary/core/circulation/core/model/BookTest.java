package com.ffr.booklibrary.core.circulation.core.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private static Book createBook() {
        return Book.create(new BookId(UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")),
                new InventoryNumber(UUID.randomUUID().toString()));
    }

    @Test
    void issueToUser_works() {
        Book book = createBook();
        UserId userId = UserId.builder().id(UUID.randomUUID()).username("John Doe").build();

        BookIssue issue = book.issueToUser(userId);

        assertNotNull(issue);
        assertTrue(book.isCurrentlyIssued());
    }

    @Test
    void issueToUser_fails_already_issued() {
        Book book = createBook();
        UserId userId = UserId.builder().id(UUID.randomUUID()).username("John Doe").build();
        BookIssue issue = book.issueToUser(userId);

        UserId anotherUserId = UserId.builder().id(UUID.randomUUID()).username("John Doe").build();
        assertThrows(BookAlreadyLentException.class, () -> book.issueToUser(userId));
    }
}