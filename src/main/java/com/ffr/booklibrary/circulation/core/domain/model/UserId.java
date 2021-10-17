package com.ffr.booklibrary.circulation.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@EqualsAndHashCode
public class UserId {

    private UUID id;

}
