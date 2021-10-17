package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.BookId;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@Introspected
public class ReturnBookDto {
    @NotEmpty
    @UUIDValidate
    private String bookId;

    public BookId toBookId() {
        return new BookId(UUID.fromString(this.bookId));
    }
}
