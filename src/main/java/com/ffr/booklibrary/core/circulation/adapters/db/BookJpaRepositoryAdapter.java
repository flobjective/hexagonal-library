package com.ffr.booklibrary.core.circulation.adapters.db;

import com.ffr.booklibrary.core.circulation.core.model.Book;
import com.ffr.booklibrary.core.circulation.core.model.BookId;
import com.ffr.booklibrary.core.circulation.core.model.UserId;
import com.ffr.booklibrary.core.circulation.core.ports.outgoing.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookJpaRepositoryAdapter implements BookRepository {

    private BookJpaRepository bookJpaRepository;

    public BookJpaRepositoryAdapter(final BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public Optional<Book> find(final BookId book) {
        return bookJpaRepository.findById(book.id()).map(JpaBook::toBook);
    }

    @Override
    public Book save(final Book book) {
        return bookJpaRepository.save(JpaBook.from(book)).toBook();
    }

    @Override
    public List<Book> listAvailableBooks() {
        return this.bookJpaRepository.findAvailable().stream().map(JpaBook::toBook).collect(Collectors.toList());
    }

    @Override
    public List<Book> listIssuedBooks(final UserId userId) {
        return this.bookJpaRepository.findIssuedByUserId(userId).stream()
                .map(JpaBook::toBook).collect(Collectors.toList());
    }

}
