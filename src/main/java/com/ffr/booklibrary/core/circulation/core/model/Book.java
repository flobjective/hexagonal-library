package com.ffr.booklibrary.core.circulation.core.model;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
@Accessors(fluent = true)
public class Book extends BaseEntity {

    private BookId bookId;
    private InventoryNumber inventoryNumber;
    private BookIssue currentBookIssue;

    public BookIssue issueToUser(final UserId userId) {
        if (this.currentBookIssue != null) {
            throw new BookAlreadyLentException(this.bookId);
        }
        this.currentBookIssue = BookIssue.builder().userId(userId).build();
        return this.currentBookIssue;
    }

    public void returnBook(final BookIssue issue) {
        if (this.currentBookIssue == null || !this.currentBookIssue.equals(issue)) {
            throw new IllegalStateException("Nope");
        } else {
            this.currentBookIssue = null;
        }
    }

    public static Book create(final BookId bookId, final InventoryNumber inventoryNumber) {
        return Book.builder().id(UUID.randomUUID()).bookId(bookId).inventoryNumber(inventoryNumber).build();
    }

    public boolean isCurrentlyIssued() {
        return this.currentBookIssue != null;
    }

}
