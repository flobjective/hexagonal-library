package com.ffr.booklibrary.core.circulation.adapters.db;

import com.ffr.booklibrary.core.circulation.core.model.BookIssue;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "circulation_book_issue")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "isCurrent", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "false")
@Data

public class JpaBookIssue {

    @Id
    private UUID id;

    private Instant expirationTime;

    @ManyToOne
    @JoinColumn(name = "userId")
    private JpaUserId user;

    @OneToMany(mappedBy = "issueId")
    private List<JpaBookRenewal> renewals;

    @Column(insertable = false, updatable = false)
    private boolean isCurrent;

    public BookIssue toBookIssue() {
        return BookIssue.builder().userId(this.user.toUserId()).build();
    }
}
