package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.AvailableBookReadModel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableBooksResponse {

  private List<AvailableBookDto> availableBooks;

  public static AvailableBooksResponse of(final List<AvailableBookReadModel> books) {
    return new AvailableBooksResponse(
        books.stream().map(AvailableBookDto::of).collect(Collectors.toList()));
  }
}
