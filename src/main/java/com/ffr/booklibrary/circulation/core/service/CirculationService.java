package com.ffr.booklibrary.circulation.core.service;

import com.ffr.booklibrary.circulation.core.model.*;
import com.ffr.booklibrary.circulation.core.model.exceptions.BookNotFoundException;
import com.ffr.booklibrary.circulation.core.ports.incoming.*;
import com.ffr.booklibrary.circulation.core.ports.outgoing.BookRepository;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.time.Clock;
import java.util.List;

@AllArgsConstructor
public class CirculationService implements AddBookToCirculation, IssueBook, ReturnBook, ListIssuedBooks {

    private final Clock clock;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void issueBook(final IssueBookCommand issueBookCommand) {
        Book book = this.bookRepository.find(issueBookCommand.getBookId()).orElseThrow(() -> new BookNotFoundException());
        book.issueToUser(issueBookCommand.getUserId(), this.clock);
        this.bookRepository.save(book);
    }

    @Override
    public void returnBook(final ReturnBookCommand returnBookCommand) {
        Book book = this.bookRepository.find(returnBookCommand.getBookId()).orElseThrow(() -> new BookNotFoundException());
        book.returnBook();
        this.bookRepository.save(book);
    }

    @Override
    public List<BookReadModel> listIssuedBooks(final UserId userId) {
        return this.bookRepository.listIssuedBooks(userId);
    }

    @Override
    public void addBookToCirculation(final InventoryNumber inventoryNumber) {
        Book newBook = Book.create(inventoryNumber);
        this.bookRepository.insert(newBook);
    }
}
