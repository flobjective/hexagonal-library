package com.ffr.booklibrary.circulation.core.application.services;

import com.ffr.booklibrary.circulation.core.application.ports.incoming.*;
import com.ffr.booklibrary.circulation.core.application.ports.outgoing.BookRepository;
import com.ffr.booklibrary.circulation.core.domain.model.Book;
import com.ffr.booklibrary.circulation.core.domain.model.BookReadModel;
import com.ffr.booklibrary.circulation.core.domain.model.InventoryNumber;
import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import com.ffr.booklibrary.circulation.core.domain.model.exceptions.BookNotFoundException;
import io.micronaut.context.event.ApplicationEventPublisher;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.time.Clock;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class CirculationService implements AddBookToCirculation, IssueBook, ReturnBook, ListIssuedBooks {

    private final Clock clock;
    private final BookRepository bookRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public void issueBook(final IssueBookCommand issueBookCommand) {
        Book book = this.bookRepository.find(issueBookCommand.getBookId()).orElseThrow(()
                -> new BookNotFoundException(issueBookCommand.getBookId()));
        var result = book.issueToUser(issueBookCommand.getUserId());
        this.bookRepository.save(book);
//        this.applicationEventPublisher.publishEvent(result.event());
    }

    @Override
    @Transactional
    public void returnBook(final ReturnBookCommand returnBookCommand) {
        Book book = this.bookRepository.find(returnBookCommand.bookId()).orElseThrow(()
                -> new BookNotFoundException(returnBookCommand.bookId()));
        book.returnBook(new UserId(UUID.randomUUID()));
        this.bookRepository.save(book);
    }

    @Override
    public List<BookReadModel> listIssuedBooks(final UserId userId) {
        return this.bookRepository.listIssuedBooks(userId);
    }

    @Override
    @Transactional
    public void addBookToCirculation(final InventoryNumber inventoryNumber) {
        Book newBook = Book.create(this.clock, inventoryNumber);
        this.bookRepository.insert(newBook);
    }
}
