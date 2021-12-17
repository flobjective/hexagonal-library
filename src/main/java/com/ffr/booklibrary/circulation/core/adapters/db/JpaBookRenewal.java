package com.ffr.booklibrary.circulation.core.adapters.db;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "circulation_book_issue_renewal")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JpaBookRenewal {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne private JpaBookIssue issueId;

  private Instant renewalTime;
}
