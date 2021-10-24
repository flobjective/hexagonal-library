package com.ffr.booklibrary.inventory.core.application.ports.outgoing;

import com.ffr.booklibrary.shared.events.BaseDomainEvent;

import java.util.List;

public interface EventsRepository {

    void save(BaseDomainEvent event);

    List<BaseDomainEvent> findAllUnpublished();
}
