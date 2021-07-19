package com.ffr.booklibrary.circulation.core.adapters.db;

import com.ffr.booklibrary.circulation.core.model.Book;
import com.ffr.booklibrary.circulation.core.model.BookId;
import com.ffr.booklibrary.circulation.core.model.BookReadModel;
import com.ffr.booklibrary.circulation.core.model.UserId;
import com.ffr.booklibrary.circulation.core.ports.outgoing.BookRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
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
    public Book insert(Book book) {
        return bookJpaRepository.save(JpaBook.from(book)).toBook();
    }

    @Override
    public Book save(final Book book) {
        var existing = bookJpaRepository.findById(book.id()).orElseThrow();
        existing.setCurrentIssue(JpaBook.from(book).getCurrentIssue());
        return bookJpaRepository.update(existing).toBook();
    }

    @Override
    public List<BookReadModel> listIssuedBooks(final UserId userId) {
        return this.bookJpaRepository.findIssuedByUserId(userId.id()).stream()
                .map(JpaBook::toReadModel).collect(Collectors.toList());
    }

}
