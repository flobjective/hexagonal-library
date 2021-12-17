package com.ffr.booklibrary.inventory.core.application.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.ffr.booklibrary.inventory.core.application.ports.incoming.RegisterBookCommand;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookDetailsProvider;
import com.ffr.booklibrary.inventory.core.domain.model.*;
import com.ffr.booklibrary.shared.events.BookRegistrationCompleted;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookServiceTest {

  private final Book mostlyHarmless =
      Book.builder()
          .title(new BookTitle("Mostly harmless"))
          .inventoryNumber(InventoryNumber.create())
          .bookIdentification(
              BookIdentification.builder()
                  .isbn10(new Isbn10("0345418778"))
                  .isbn13(new Isbn13("978-0345418777"))
                  .build())
          .build();
  private final List<Book> books = List.of(mostlyHarmless);

  private BookDetailsProvider bookDetailsProvider;
  private InMemoryInventoryRepository bookRepository;

  @BeforeEach
  public void init() {
    this.bookDetailsProvider = new FakeBookDetailsProvider(books);
    this.bookRepository = new InMemoryInventoryRepository(books);
  }

  @Test
  @DisplayName("Should save book")
  public void shouldSaveBook() {
    var bookService =
        new BookService(
            this.bookDetailsProvider, this.bookRepository, new FakeBookEventPublisher());
    var addedBook = bookService.registerBook(new RegisterBookCommand("978-0345418777"));
    assertThat(addedBook).isNotEmpty();
    assertThat(bookRepository.getBookById(addedBook.get().id())).isNotNull();
  }

  @Test
  @DisplayName("Should save book and publish event")
  public void shouldSaveBookAndPublishEvent() {
    var fakeBookEventPublisher = new FakeBookEventPublisher();
    var bookService =
        new BookService(this.bookDetailsProvider, this.bookRepository, fakeBookEventPublisher);
    var addedBook = bookService.registerBook(new RegisterBookCommand("978-0345418777"));
    assertThat(addedBook).isNotEmpty();
    assertThat(
            fakeBookEventPublisher.containsEvent(
                (event) ->
                    event instanceof BookRegistrationCompleted
                        && ((BookRegistrationCompleted) event)
                            .bookId()
                            .equals(addedBook.get().id())))
        .isTrue();
  }

  @Test
  @DisplayName("Should not save an unknown book in repository")
  public void shouldNotSaveUnknownBook() {
    var bookService =
        new BookService(
            this.bookDetailsProvider, this.bookRepository, new FakeBookEventPublisher());
    var addedBook = bookService.registerBook(new RegisterBookCommand("123"));
    assertThat(addedBook).isEmpty();
  }

  @Test
  @DisplayName("Should list all books")
  public void shouldListBooks() {
    var bookService = new BookService(this.bookDetailsProvider, this.bookRepository, null);
    var books = bookService.listBooks();
    assertThat(books).hasSize(1);
    assertThat(books.get(0)).isNotNull();
  }
}
