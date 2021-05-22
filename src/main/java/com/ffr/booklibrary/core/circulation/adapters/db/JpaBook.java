package com.ffr.booklibrary.core.circulation.adapters.db;

import com.ffr.booklibrary.core.circulation.core.model.Book;
import com.ffr.booklibrary.core.circulation.core.model.BookId;
import com.ffr.booklibrary.core.circulation.core.model.InventoryNumber;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "circulation_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JpaBook {

    @Id
    private UUID id;

    @Column(unique = true)
    private String inventoryNumber;

    @OneToOne(mappedBy = "book")
    private JpaCurrentBookIssue currentIssue;

    public static JpaBook from(final Book book) {
        return new JpaBook(book.id(), book.inventoryNumber().toString(), null);
    }

    public Book toBook() {
        return Book.builder().bookId(new BookId(this.id))
                .currentBookIssue(currentIssue != null ? currentIssue.toBookIssue() : null)
                .build();
    }
}
