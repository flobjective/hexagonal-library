package com.ffr.booklibrary.core.circulation.core.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserId extends BaseEntity {

    private String username;

}
