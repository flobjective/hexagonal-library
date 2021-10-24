package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.application.ports.incoming.*;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import javax.inject.Inject;
import javax.validation.Valid;

@Validated
@Controller(value = "/circulation")
public class CirculationController {

  @Inject private ListIssuedBooks listIssuedBooks;

  @Inject private IssueBook issueBook;

  @Inject private ReturnBook returnBook;

  @Post(value = "/issued")
  public IssuedBooksResponse listIssuedBooks(@Body @Valid IssuedToUserIdDto issuedToUserIdDto) {
    return IssuedBooksResponse.of(listIssuedBooks.listIssuedBooks(issuedToUserIdDto.toUserId()));
  }

  @Post(value = "/issue")
  public void issueToUser(@Body @Valid IssueBookToUserDto issueToUserDto) {
    issueBook.issueBook(new IssueBookCommand(issueToUserDto.toBookId(), issueToUserDto.toUserId()));
  }

  @Post(value = "/return")
  public void returnBook(@Body @Valid ReturnBookDto returnBookDto) {
    returnBook.returnBook(new ReturnBookCommand(returnBookDto.toBookId()));
  }
}
