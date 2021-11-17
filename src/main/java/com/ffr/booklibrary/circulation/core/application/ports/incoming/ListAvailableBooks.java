package com.ffr.booklibrary.circulation.core.application.ports.incoming;

import com.ffr.booklibrary.circulation.core.domain.model.AvailableBookReadModel;
import java.util.List;

public interface ListAvailableBooks {

  List<AvailableBookReadModel> listAvailableBooks();
}
