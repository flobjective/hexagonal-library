package com.ffr.booklibrary.core.inventory.core;

import com.ffr.booklibrary.core.inventory.FakeBookDetailsProvider;
import com.ffr.booklibrary.core.inventory.FakeBookEventPublisher;
import com.ffr.booklibrary.core.inventory.InMemoryInventoryRepository;
import com.ffr.booklibrary.core.inventory.core.model.*;
import com.ffr.booklibrary.core.inventory.core.ports.outgoing.BookDetailsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookServiceTest {

    private Book mostlyHarmless = Book.builder().title(new BookTitle("Mostly harmless"))
            .bookIdentification(BookIdentification.builder()
                    .isbn10(new Isbn10("0345418778"))
                    .isbn13(new Isbn13("978-0345418777"))
                    .build())
            .build();
    private List<Book> books = List.of(mostlyHarmless);

    private BookDetailsProvider bookDetailsProvider;
    private InMemoryInventoryRepository bookRepository;

    @BeforeEach
    public void init() {
        this.bookDetailsProvider = new FakeBookDetailsProvider(books);
        this.bookRepository = new InMemoryInventoryRepository(books);
    }

    @Test
    @DisplayName("Should save a book in repository")
    public void shouldSaveExistingBook() {
        var bookService = new BookService(this.bookDetailsProvider, this.bookRepository, new FakeBookEventPublisher());
        var addedBook = bookService.addBook(new AddBookCommand("978-0345418777"));
        assertThat(addedBook).isNotEmpty();
        assertThat(bookRepository.getBookById(1L)).isNotNull();
    }

    @Test
    @DisplayName("Should not save an unknown book in repository")
    public void shouldNotSaveUnknownBook() {
        var bookService = new BookService(this.bookDetailsProvider, this.bookRepository, new FakeBookEventPublisher());
        var addedBook = bookService.addBook(new AddBookCommand("123"));
        assertThat(addedBook).isEmpty();
        assertThat(bookRepository.getBookById(1L)).isNull();
    }

    @Test
    @DisplayName("Should save a book in repository")
    public void shouldListBooks() {
        var bookService = new BookService(this.bookDetailsProvider, this.bookRepository, null);
        var books = bookService.listBooks();
        assertThat(books).hasSize(1);
        assertThat(books.get(0)).isNotNull();
    }
}