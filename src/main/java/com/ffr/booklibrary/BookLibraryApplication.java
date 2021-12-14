package com.ffr.booklibrary;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(title = "LibraryApplication", version = "0.0", description = "Library API"))
public class BookLibraryApplication {

  public static void main(String[] args) {
    Micronaut.run(BookLibraryApplication.class);
  }
}
