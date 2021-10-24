package com.ffr.booklibrary.inventory.core.application.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.ffr.booklibrary.inventory.core.application.ports.incoming.AddBookCommand;
import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookDetailsProvider;
import com.ffr.booklibrary.inventory.core.domain.model.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookServiceTest {

  private Book mostlyHarmless =
      Book.builder()
          .title(new BookTitle("Mostly harmless"))
          .inventoryNumber(InventoryNumber.create())
          .bookIdentification(
              BookIdentification.builder()
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
    var bookService =
        new BookService(
            this.bookDetailsProvider, this.bookRepository, new FakeBookEventPublisher());
    var addedBook = bookService.addBook(new AddBookCommand("978-0345418777"));
    assertThat(addedBook).isNotEmpty();
    assertThat(bookRepository.getBookById(addedBook.get().id())).isNotNull();
  }

  @Test
  @DisplayName("Should not save an unknown book in repository")
  public void shouldNotSaveUnknownBook() {
    var bookService =
        new BookService(
            this.bookDetailsProvider, this.bookRepository, new FakeBookEventPublisher());
    var addedBook = bookService.addBook(new AddBookCommand("123"));
    assertThat(addedBook).isEmpty();
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
