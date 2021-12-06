package com.ffr.booklibrary.inventory.core.adapters.api;

import com.ffr.booklibrary.inventory.core.application.ports.incoming.RegisterBook;
import com.ffr.booklibrary.inventory.core.application.ports.incoming.ListBooks;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.validation.Validated;
import javax.inject.Inject;
import javax.validation.Valid;

@Validated
@Controller("/book")
public class BookController {

  @Inject private RegisterBook registerBook;

  @Inject private ListBooks listBooks;

  @Post("/add")
  public RegisterBookResponse addNewBook(@Body @Valid final RegisterBookBody registerBookBody) {
    var book = this.registerBook.registerBook(registerBookBody.toCommand());
    return RegisterBookResponse.of(
        book.orElseThrow(() -> new BookNotFoundException(registerBookBody.isbn())));
  }

  @Get()
  public ListBookResponse listBooks() {
    return ListBookResponse.of(this.listBooks.listBooks());
  }

  @Error
  public HttpResponse<ErrorBody> bookNotFoundError(
      final HttpRequest request, final BookNotFoundException e) {
    return HttpResponse.status(HttpStatus.NOT_FOUND).body(new ErrorBody(e.getMessage()));
  }
}
