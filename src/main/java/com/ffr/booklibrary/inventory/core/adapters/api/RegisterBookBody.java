package com.ffr.booklibrary.inventory.core.adapters.api;

import com.ffr.booklibrary.inventory.core.application.ports.incoming.RegisterBookCommand;
import com.ffr.booklibrary.inventory.core.application.ports.incoming.Isbn;
import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Builder
@Getter
@Accessors(fluent = true)
@Introspected
public class RegisterBookBody {

  @NotNull @Isbn private String isbn;

  public RegisterBookCommand toCommand() {
    return new RegisterBookCommand(this.isbn);
  }
}
