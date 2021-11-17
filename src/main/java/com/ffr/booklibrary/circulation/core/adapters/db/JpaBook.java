package com.ffr.booklibrary.circulation.core.adapters.db;

import com.ffr.booklibrary.circulation.core.domain.model.*;

import java.time.Clock;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "circulation_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JpaBook {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(unique = true)
  private String inventoryNumber;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "current_book_id")
  private JpaBookIssue currentIssue;

  public static JpaBook from(final Book book) {
    return new JpaBook(
        book.id(),
        book.inventoryNumber().toString(),
        book.currentBookIssue() == null ? null : JpaBookIssue.from(book.currentBookIssue()));
  }

  public Book toBook() {
    return Book.builder()
        .id(this.id)
        .clock(Clock.systemUTC())
        .inventoryNumber(new InventoryNumber(this.inventoryNumber))
        .currentBookIssue(currentIssue != null ? currentIssue.toBookIssue() : null)
        .build();
  }

  public BookReadModel toReadModel() {
    return new BookReadModel(new BookId(this.id), this.currentIssue.getExpirationTime());
  }

  public AvailableBookReadModel toAvailableReadModel() {
    return new AvailableBookReadModel(
        new BookId(this.id), new InventoryNumber(this.inventoryNumber));
  }
}
