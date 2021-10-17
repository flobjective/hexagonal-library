package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.BookId;
import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Transient;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Introspected
@Data
public class IssueBookToUserDto {

    @NotNull
    @UUIDValidate
    private String bookId;

    @NotNull
    @UUIDValidate
    private String userId;

    @Transient
    BookId toBookId() {
        return new BookId(UUID.fromString(bookId));
    }

    @Transient
    UserId toUserId() {
        return new UserId(UUID.fromString(this.userId));
    }
}
