package com.ffr.booklibrary.core.circulation.adapters.api;

import com.ffr.booklibrary.core.circulation.core.ports.incoming.IssueBook;
import com.ffr.booklibrary.core.circulation.core.ports.incoming.ListAvailableBooks;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller(value = "/circulation")
public class CirculationController {

    @Inject
    private ListAvailableBooks listAvailableBooks;

    @Inject
    private IssueBook issueBook;

    @Get(value = "/available")
    public AvailableBooksResponse listAvailableBooks() {
        return AvailableBooksResponse.of(listAvailableBooks.listAvailableBooks());
    }

    @Post(value = "/issue")
    public void issueToUser(@Body @Valid IssueBookToUserDto issueToUserDto) {
        issueBook.issueBook(issueToUserDto.getBookId(), issueToUserDto.getUserId());
    }

}
