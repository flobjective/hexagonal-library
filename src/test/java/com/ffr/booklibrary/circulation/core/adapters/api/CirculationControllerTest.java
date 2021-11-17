package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.application.ports.outgoing.BookRepository;
import com.ffr.booklibrary.circulation.core.application.ports.outgoing.UserRepository;
import com.ffr.booklibrary.circulation.core.domain.model.Book;
import com.ffr.booklibrary.circulation.core.domain.model.InventoryNumber;
import com.ffr.booklibrary.circulation.core.domain.model.User;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.Clock;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
class CirculationControllerTest {

  private final String BASE_PATH = "/circulation/";

  @Inject
  @Client(BASE_PATH)
  RxHttpClient client;

  @Inject private BookRepository bookRepository;

  @Inject private UserRepository userRepository;

  @Test
  void listAvailableBooks_noBooks() {
    HttpRequest<String> request = HttpRequest.GET("/available");
    var httpResponse = client.toBlocking().exchange(request);
    assertEquals(HttpStatus.OK, httpResponse.status());
    assertNull(httpResponse.body());
  }

  @Test
  void listAvailableBooks_someBooks() {
    bookRepository.insert(
        Book.create(Clock.systemUTC(), new InventoryNumber(UUID.randomUUID().toString())));

    HttpResponse<AvailableBooksResponse> response =
        client.toBlocking().exchange(HttpRequest.GET("/available"), AvailableBooksResponse.class);

    assertThat(response)
        .isNotNull()
        .extracting(HttpResponse::body)
        .extracting(AvailableBooksResponse::getAvailableBooks)
        .extracting(List::size)
        .isEqualTo(1);
  }

  @Test
  void issueBook_success() {
    var book =
        bookRepository.insert(
            Book.create(Clock.systemUTC(), new InventoryNumber(UUID.randomUUID().toString())));

    var user = userRepository.insert(User.create("John Doe"));

    HttpResponse<String> response =
        client
            .toBlocking()
            .exchange(
                HttpRequest.POST(
                    "/available/" + book.id().toString() + "/issue",
                    new IssueBookToUserDto(user.id().toString())),
                String.class);

    assertThat(response).isNotNull();
  }

  @Test
  void issueBook_bookNotFound() {
    var user = userRepository.insert(User.create("John Doe"));

    var exc =
        assertThrows(
            HttpClientResponseException.class,
            () ->
                client
                    .toBlocking()
                    .exchange(
                        HttpRequest.POST(
                            "/available/" + UUID.randomUUID() + "/issue",
                            new IssueBookToUserDto(user.id().toString())),
                        String.class));

    assertThat(exc.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  void returnBook_success() {
    var book =
        bookRepository.insert(
            Book.create(Clock.systemUTC(), new InventoryNumber(UUID.randomUUID().toString())));

    var user = userRepository.insert(User.create("John Doe"));
    client
        .toBlocking()
        .exchange(
            HttpRequest.POST(
                "/available/" + book.id().toString() + "/issue",
                new IssueBookToUserDto(user.id().toString())));

    HttpResponse<String> response =
        client
            .toBlocking()
            .exchange(
                HttpRequest.POST(
                    "/issued/" + book.id().toString() + "/return",
                    new ReturnBookDto(user.id().toString())),
                String.class);

    assertThat(response).extracting(HttpResponse::getStatus).isEqualTo(HttpStatus.OK);
  }
}
