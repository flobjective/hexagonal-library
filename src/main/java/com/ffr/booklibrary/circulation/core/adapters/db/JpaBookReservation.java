package com.ffr.booklibrary.circulation.core.adapters.db;

import com.ffr.booklibrary.circulation.core.domain.model.BookReservation;
import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "circulation_book_reservation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JpaBookReservation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column private UUID userId;

  @Column private Instant expirationTime;

  public static JpaBookReservation from(final BookReservation reservation) {
    return new JpaBookReservation(
        reservation.id(), reservation.userId().id(), reservation.expirationTime());
  }

  public BookReservation toBookReservation() {
    return BookReservation.builder()
        .id(this.id)
        .userId(new UserId(this.userId))
        .expirationTime(this.expirationTime)
        .build();
  }
}
