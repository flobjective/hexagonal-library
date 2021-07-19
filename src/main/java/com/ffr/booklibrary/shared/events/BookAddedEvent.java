package com.ffr.booklibrary.shared.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@AllArgsConstructor
@Accessors(fluent = true)
public class BookAddedEvent {

    private UUID bookId;

    private String inventoryNumber;
}
