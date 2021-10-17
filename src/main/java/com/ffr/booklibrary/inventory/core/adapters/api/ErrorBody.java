package com.ffr.booklibrary.inventory.core.adapters.api;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public class ErrorBody {

    private final String errorMessage;

}
