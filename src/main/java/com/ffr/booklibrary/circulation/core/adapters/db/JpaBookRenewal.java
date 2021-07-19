package com.ffr.booklibrary.circulation.core.adapters.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity(name = "circulation_book_issue_renewal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JpaBookRenewal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private JpaBookIssue issueId;

    private Instant renewalTime;

}
