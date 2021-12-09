package com.ffr.booklibrary.circulation.core.application.ports.incoming;

public interface ReserveBook {

  void reserveBook(ReserveBookCommand reserveBookCommand);
}
