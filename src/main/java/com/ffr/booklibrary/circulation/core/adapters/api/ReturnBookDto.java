package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.BookId;
import io.micronaut.core.annotation.Introspected;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Introspected
public class ReturnBookDto {
  @NotEmpty @UUIDValidate private String bookId;

  public BookId toBookId() {
    return new BookId(UUID.fromString(this.bookId));
  }
}
