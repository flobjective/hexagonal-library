package com.ffr.booklibrary.circulation.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.time.Instant;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class BookRenewal {

    private final Instant renewalTime;

    private final Duration renewalDuration;

    public BookRenewal(final Duration duration) {
        this.renewalTime = Instant.now();
        this.renewalDuration = duration;
    }
}
