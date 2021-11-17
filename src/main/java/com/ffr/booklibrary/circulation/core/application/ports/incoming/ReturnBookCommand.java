package com.ffr.booklibrary.circulation.core.application.ports.incoming;

import com.ffr.booklibrary.circulation.core.domain.model.BookId;
import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public class ReturnBookCommand {
  private final BookId bookId;
  private final UserId userId;
}
