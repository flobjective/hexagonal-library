package com.ffr.booklibrary.inventory.core.adapters.api;

import com.ffr.booklibrary.inventory.core.application.ports.incoming.AddBookCommand;
import com.ffr.booklibrary.inventory.core.application.ports.incoming.Isbn;
import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Accessors(fluent = true)
@Introspected
public class AddBookBody {

    @NotNull
    @Isbn
    private String isbn;

    public AddBookCommand toCommand() {
        return new AddBookCommand(this.isbn);
    }
}
