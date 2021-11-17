package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.AvailableBookReadModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailableBookDto {

  private String bookId;

  private String inventoryNumber;

  public static AvailableBookDto of(final AvailableBookReadModel availableBookReadModel) {
    return new AvailableBookDto(
        availableBookReadModel.bookId().toString(),
        availableBookReadModel.inventoryNumber().toString());
  }
}
