package com.ffr.booklibrary.core.inventory.adapters.api;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public class ErrorBody {

    private final String errorMessage;

}
