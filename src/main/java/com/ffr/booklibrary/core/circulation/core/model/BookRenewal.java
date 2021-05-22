package com.ffr.booklibrary.core.circulation.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class BookRenewal {

    private final Instant renewalTime;

    public BookRenewal() {
        this.renewalTime = Instant.now();
    }
}
