package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.application.ports.incoming.*;
import com.ffr.booklibrary.circulation.core.domain.model.BookId;
import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import java.util.UUID;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@Controller(value = "/circulation")
public class CirculationController {

  @Inject private ListIssuedBooks listIssuedBooks;

  @Inject private ListAvailableBooks listAvailableBooks;

  @Inject private GetAvailableBook getAvailableBook;

  @Inject private IssueBook issueBook;

  @Inject private com.ffr.booklibrary.circulation.core.application.ports.incoming.ReturnBook returnBook;

  @Inject private com.ffr.booklibrary.circulation.core.application.ports.incoming.ReserveBook reserveBook;

  @Get(value = "/available")
  public AvailableBooksResponse listAvailableBooks() {
    return AvailableBooksResponse.of(listAvailableBooks.listAvailableBooks());
  }

  @Get(value = "/available/{bookId}")
  public AvailableBook getAvailableBook(@QueryValue @NotBlank @UUIDValidate String bookId) {
    return AvailableBook.of(
        getAvailableBook.getAvailableBook(new BookId(UUID.fromString(bookId))));
  }

  @Get(value = "/issued")
  public IssuedBooksResponse listIssuedBooks(@QueryValue @NotBlank @UUIDValidate String userId) {
    return IssuedBooksResponse.of(
        listIssuedBooks.listIssuedBooks(new UserId(UUID.fromString(userId))));
  }

  @Post(value = "/available/{bookId}/issue")
  public void issueToUser(
          @Body @Valid IssueBookToUser issueToUserDto, @NotBlank @UUIDValidate String bookId) {
    issueBook.issueBook(
        new IssueBookCommand(new BookId(UUID.fromString(bookId)), issueToUserDto.toUserId()));
  }

  @Post(value = "/issued/{bookId}/return")
  public void returnBook(
      @NotBlank @UUIDValidate String bookId, @Body @Valid ReturnBook returnBook) {
    this.returnBook.returnBook(
        new ReturnBookCommand(new BookId(UUID.fromString(bookId)), returnBook.toUserId()));
  }

  @Post(value = "/issued/{bookId}/reserve")
  public void reserveBook(
      @NotBlank @UUIDValidate String bookId, @Body @Valid ReserveBook reserveBook) {
    this.reserveBook.reserveBook(
        new ReserveBookCommand(new BookId(UUID.fromString(bookId)), reserveBook.toUserId()));
  }
}
