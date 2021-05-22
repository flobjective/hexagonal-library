package com.ffr.booklibrary.core.inventory.adapters.api;

import com.ffr.booklibrary.core.inventory.core.model.AddBookCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Jacksonized
@Builder
@Getter
@Accessors(fluent = true)
public class AddBookBody {

    @NotNull
    @NotBlank
    private String isbn;

    public AddBookCommand toCommand() {
        return new AddBookCommand(this.isbn);
    }
}
