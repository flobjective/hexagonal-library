package com.ffr.booklibrary.core.circulation.adapters.db;

import io.micronaut.data.annotation.Where;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@DiscriminatorValue(value = "true")
@Where("isCurrent is true")
public class JpaCurrentBookIssue extends JpaBookIssue {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private JpaBook book;
}
