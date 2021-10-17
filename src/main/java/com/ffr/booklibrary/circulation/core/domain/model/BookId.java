package com.ffr.booklibrary.circulation.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class BookId {

    private UUID id;

}
