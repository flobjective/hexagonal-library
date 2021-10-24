package com.ffr.booklibrary.inventory.core.application.services;

import com.ffr.booklibrary.inventory.core.application.ports.outgoing.BookEventPublisher;
import com.ffr.booklibrary.shared.events.BaseDomainEvent;

import java.util.List;

public class FakeBookEventPublisher implements BookEventPublisher {


    @Override
    public void publishEvents(List<BaseDomainEvent> events) {

    }
}
