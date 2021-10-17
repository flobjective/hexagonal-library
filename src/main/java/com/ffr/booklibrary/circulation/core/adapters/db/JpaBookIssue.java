package com.ffr.booklibrary.circulation.core.adapters.db;

import com.ffr.booklibrary.circulation.core.domain.model.BookIssue;
import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "circulation_book_issue")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JpaBookIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private Instant expirationTime;

    @Column
    private UUID userId;

    @OneToMany(mappedBy = "issueId")
    private List<JpaBookRenewal> renewals;

    public static JpaBookIssue from(final BookIssue bookIssue) {
        return JpaBookIssue.builder().id(bookIssue.id())
                .userId(bookIssue.userId().id())
                .expirationTime(bookIssue.expirationTime())
//                .renewals(bookIssue.renewals())
                .build();
    }

    public BookIssue toBookIssue() {
        return BookIssue.builder().userId(new UserId(this.userId)).build();
    }
}
