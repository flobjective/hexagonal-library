package com.ffr.booklibrary.inventory.core;

import com.ffr.booklibrary.inventory.core.model.AddBookCommand;
import com.ffr.booklibrary.inventory.core.model.Book;
import com.ffr.booklibrary.inventory.core.ports.incoming.AddBook;
import com.ffr.booklibrary.inventory.core.ports.incoming.ListBooks;
import com.ffr.booklibrary.inventory.core.ports.outgoing.BookDetailsProvider;
import com.ffr.booklibrary.inventory.core.ports.outgoing.BookEventPublisher;
import com.ffr.booklibrary.inventory.core.ports.outgoing.BookRepository;

import java.util.List;
import java.util.Optional;

public class BookService implements AddBook, ListBooks {

    private BookDetailsProvider bookDetailsProvider;
    private BookRepository bookRepository;
    private BookEventPublisher bookEventPublisher;

    public BookService(final BookDetailsProvider bookDetailsProvider, final BookRepository bookRepository,
                       final BookEventPublisher bookEventPublisher) {
        this.bookDetailsProvider = bookDetailsProvider;
        this.bookRepository = bookRepository;
        this.bookEventPublisher = bookEventPublisher;
    }

    @Override
    public Optional<Book> addBook(final AddBookCommand command) {
        var book = this.bookDetailsProvider.find(command.isbn());
        var savedBook = book.map(Book::newBook).map(b -> this.bookRepository.save(b));
        savedBook.ifPresent(presentBook -> this.bookEventPublisher.bookAdded(presentBook));
        return savedBook;
    }

    @Override
    public List<Book> listBooks() {
        return this.bookRepository.list();
    }
}
