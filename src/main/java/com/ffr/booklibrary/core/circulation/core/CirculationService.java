package com.ffr.booklibrary.core.circulation.core;

import com.ffr.booklibrary.core.circulation.core.model.*;
import com.ffr.booklibrary.core.circulation.core.ports.incoming.*;
import com.ffr.booklibrary.core.circulation.core.ports.outgoing.BookRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CirculationService implements AddBookToCirculation, IssueBook, ReturnBook, ListAvailableBooks, ListIssuedBooks {

    private final BookRepository bookRepository;

    @Override
    public void issueBook(final BookId bookId, final UserId userId) {
        Book book = this.bookRepository.find(bookId).orElseThrow(() -> new BookNotFoundException());
        book.issueToUser(userId);
        this.bookRepository.save(book);
    }

    @Override
    public void returnBook(final BookId bookId, final BookIssue bookIssue) {
        Book book = this.bookRepository.find(bookId).orElseThrow(() -> new BookNotFoundException());
        book.returnBook(bookIssue);
        this.bookRepository.save(book);
    }

    @Override
    public List<Book> listAvailableBooks() {
        return this.bookRepository.listAvailableBooks();
    }

    @Override
    public List<Book> listIssuedBooks(final UserId userId) {
        return this.bookRepository.listIssuedBooks(userId);
    }

    @Override
    public void addBookToCirculation(final BookId bookId, final InventoryNumber inventoryNumber) {
        Book newBook = Book.create(bookId, inventoryNumber);
        this.bookRepository.save(newBook);
    }
}
