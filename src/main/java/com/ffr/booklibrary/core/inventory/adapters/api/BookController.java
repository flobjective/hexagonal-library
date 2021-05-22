package com.ffr.booklibrary.core.inventory.adapters.api;

import com.ffr.booklibrary.core.inventory.core.ports.incoming.AddBook;
import com.ffr.booklibrary.core.inventory.core.ports.incoming.ListBooks;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Controller("/book")
public class BookController {

    @Inject
    private AddBook addBook;

    @Inject
    private ListBooks listBooks;

    @Post("/add")
    @Transactional
    public AddBookResponse addNewBook(@Body final AddBookBody addBookBody) {
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
