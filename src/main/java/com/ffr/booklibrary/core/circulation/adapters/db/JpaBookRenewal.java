package com.ffr.booklibrary.core.circulation.adapters.db;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.util.UUID;

@Entity(name = "circulation_book_issue_renewal")
@Data
public class JpaBookRenewal {

    @Id
    private UUID id;

    @ManyToOne
    private JpaBookIssue issueId;

    private Instant renewalTime;

}
