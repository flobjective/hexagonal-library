package com.ffr.booklibrary.inventory.core.adapters.db;

import com.ffr.booklibrary.inventory.core.domain.model.Book;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class BookJpaRepositoryAdapter implements BookRepository {

    private BookJpaRepository bookJpaRepository;

    public BookJpaRepositoryAdapter(final BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public Book save(final Book book) {
        return JpaBook.toBook(this.bookJpaRepository.save(JpaBook.of(book)));
    }

    @Override
    public List<Book> list() {
        return StreamSupport.stream(this.bookJpaRepository.findAll().spliterator(), false)
                .map(JpaBook::toBook).collect(Collectors.toList());
    }
}
