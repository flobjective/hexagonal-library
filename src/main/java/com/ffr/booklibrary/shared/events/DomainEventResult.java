package com.ffr.booklibrary.shared.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class DomainEventResult<T, E extends BaseDomainEvent> {

    private final E event;
    private final T result;

}
