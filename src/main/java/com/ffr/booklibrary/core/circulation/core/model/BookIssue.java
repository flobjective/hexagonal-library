package com.ffr.booklibrary.core.circulation.core.model;

import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
public class BookIssue extends BaseEntity {

    private final Instant expirationTime;

    private final UserId userId;

    private final int maximumRenewals;

    private final List<BookRenewal> renewals = new ArrayList<>();

    public boolean hasElapsed(final Instant currentTime) {
        return expirationTime.isAfter(currentTime);
    }

    public boolean canBeRenewed() {
        return this.renewals.size() < this.maximumRenewals;
    }

    public void renew() {
        if (!this.canBeRenewed()) {
            throw new BookCannotBeRenewedException();
        }
        this.renewals.add(new BookRenewal());
    }

}
