package com.ffr.booklibrary.circulation.core.model;

import com.ffr.booklibrary.circulation.core.model.exceptions.BookAlreadyIssuedException;
import com.ffr.booklibrary.circulation.core.model.exceptions.BookNotIssuedException;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.Clock;
import java.time.temporal.ChronoUnit;

@SuperBuilder
@Getter
@Accessors(fluent = true)
public class Book extends BaseEntity {

    private InventoryNumber inventoryNumber;
    private BookIssue currentBookIssue;

    public BookIssue issueToUser(final UserId userId, final Clock clock) {
        if (this.currentBookIssue != null) {
            throw new BookAlreadyIssuedException();
        }
        this.currentBookIssue = BookIssue.createIssue(userId, clock.instant().plus(1, ChronoUnit.DAYS));
        return this.currentBookIssue;
    }

    public void returnBook() {
        if (this.currentBookIssue == null) {
            throw new BookNotIssuedException();
        } else {
            this.currentBookIssue = null;
        }
    }

    public static Book create(final InventoryNumber inventoryNumber) {
        return Book.builder().inventoryNumber(inventoryNumber).build();
    }

}
