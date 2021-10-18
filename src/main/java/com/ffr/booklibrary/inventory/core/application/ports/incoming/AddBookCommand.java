package com.ffr.booklibrary.inventory.core.application.ports.incoming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class AddBookCommand {

    private final String isbn;
}
