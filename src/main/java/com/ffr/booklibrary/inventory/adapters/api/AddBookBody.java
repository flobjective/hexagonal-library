package com.ffr.booklibrary.inventory.adapters.api;

import com.ffr.booklibrary.inventory.core.model.AddBookCommand;
import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Accessors(fluent = true)
@Introspected
public class AddBookBody {

    @NotNull
    @NotBlank
    private String isbn;

    public AddBookCommand toCommand() {
        return new AddBookCommand(this.isbn);
    }
}
