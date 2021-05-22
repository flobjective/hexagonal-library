package com.ffr.booklibrary.core.circulation.adapters.api;

import com.ffr.booklibrary.core.circulation.core.model.BookId;
import com.ffr.booklibrary.core.circulation.core.model.UserId;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;

@Introspected
public class IssueBookToUserDto {

    @NotNull
    @UUID
    private String bookId;

    @NotNull
    @UUID
    private String userId;

    public BookId getBookId() {
        return new BookId(java.util.UUID.fromString(bookId));
    }

    public UserId getUserId() {
        return UserId.builder().id(java.util.UUID.fromString(this.userId)).build();
    }
}
