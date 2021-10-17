package com.ffr.booklibrary.inventory.core.adapters.openlibrary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OLBookIdentifiers {

    @JsonProperty("isbn_10")
    private String isbn10;

    @JsonProperty("isbn_13")
    private String isbn13;
}
