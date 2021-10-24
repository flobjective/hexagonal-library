package com.ffr.booklibrary.inventory.core.adapters.db;

import com.ffr.booklibrary.inventory.core.domain.model.*;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JpaBook {

  @Id private UUID id;

  @Column(unique = true)
  private String inventoryNumber;

  private String title;

  private String isbn10;

  private String isbn13;

  public static Book toBook(JpaBook jpaBook) {
    return Book.builder()
        .id(jpaBook.id)
        .title(new BookTitle(jpaBook.title))
        .inventoryNumber(new InventoryNumber(jpaBook.inventoryNumber))
        .bookIdentification(
            BookIdentification.builder()
                .isbn10(new Isbn10(jpaBook.isbn10))
                .isbn13(new Isbn13(jpaBook.isbn13))
                .build())
        .build();
  }

  static JpaBook of(final Book book) {
    UUID id = book.id() != null ? book.id() : UUID.randomUUID();
    return new JpaBook(
        id,
        book.inventoryNumber().toString(),
        book.title().title(),
        book.bookIdentification().isbn10().value(),
        book.bookIdentification().isbn13().value());
  }
}
