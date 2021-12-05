package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.application.ports.incoming.*;
import com.ffr.booklibrary.circulation.core.domain.model.BookId;
import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Validated
@Controller(value = "/circulation")
public class CirculationController {

  @Inject private ListIssuedBooks listIssuedBooks;

  @Inject private ListAvailableBooks listAvailableBooks;

  @Inject private IssueBook issueBook;

  @Inject private ReturnBook returnBook;

  @Get(value = "/available")
  public AvailableBooksResponse listAvailableBooks() {
    return AvailableBooksResponse.of(listAvailableBooks.listAvailableBooks());
  }

  @Get(value = "/issued")
  public IssuedBooksResponse listIssuedBooks(@QueryValue @NotBlank @UUIDValidate String userId) {
    return IssuedBooksResponse.of(listIssuedBooks.listIssuedBooks(new UserId(UUID.fromString(userId))));
  }

  @Post(value = "/available/{bookId}/issue")
  public void issueToUser(
      @Body @Valid IssueBookToUserDto issueToUserDto, @NotBlank @UUIDValidate String bookId) {
    issueBook.issueBook(
        new IssueBookCommand(new BookId(UUID.fromString(bookId)), issueToUserDto.toUserId()));
  }

  @Post(value = "/issued/{bookId}/return")
  public void returnBook(
      @NotBlank @UUIDValidate String bookId, @Body @Valid ReturnBookDto returnBookDto) {
    returnBook.returnBook(
        new ReturnBookCommand(new BookId(UUID.fromString(bookId)), returnBookDto.toUserId()));
  }
}
