package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.application.ports.incoming.*;
import com.ffr.booklibrary.circulation.core.domain.model.BookId;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
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
  public AvailableBooksResponse listIssuedBooks() {
    return AvailableBooksResponse.of(listAvailableBooks.listAvailableBooks());
  }

  @Post(value = "/issued")
  public IssuedBooksResponse listIssuedBooks(@Body @Valid IssuedToUserIdDto issuedToUserIdDto) {
    return IssuedBooksResponse.of(listIssuedBooks.listIssuedBooks(issuedToUserIdDto.toUserId()));
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
