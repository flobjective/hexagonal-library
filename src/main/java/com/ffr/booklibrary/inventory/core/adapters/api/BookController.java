package com.ffr.booklibrary.inventory.core.adapters.api;

import com.ffr.booklibrary.inventory.core.application.ports.incoming.AddBook;
import com.ffr.booklibrary.inventory.core.application.ports.incoming.ListBooks;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Validated
@Controller("/book")
public class BookController {

    @Inject
    private AddBook addBook;

    @Inject
    private ListBooks listBooks;

    @Post("/add")
    public AddBookResponse addNewBook(@Body @Valid final AddBookBody addBookBody) {
        var book = this.addBook.addBook(addBookBody.toCommand());
        return AddBookResponse.of(book.orElseThrow(() -> new BookNotFoundException(addBookBody.isbn())));
    }

    @Get()
    public ListBookResponse listBooks() {
        return ListBookResponse.of(this.listBooks.listBooks());
    }

    @Error
    public HttpResponse<ErrorBody> bookNotFoundError(final HttpRequest request, final BookNotFoundException e) {
        return HttpResponse.status(HttpStatus.NOT_FOUND).body(new ErrorBody(e.getMessage()));
    }
}
