package com.ffr.booklibrary.circulation.core.adapters.api;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
import java.time.Clock;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import javax.inject.Inject;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

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
    var book =
        bookRepository.insert(
            Book.create(Clock.systemUTC(), new InventoryNumber(UUID.randomUUID().toString())));

    HttpResponse<AvailableBooksResponse> response =
        client.toBlocking().exchange(HttpRequest.GET("/available"), AvailableBooksResponse.class);

    assertThat(response)
        .isNotNull()
        .extracting(HttpResponse::body)
        .extracting(AvailableBooksResponse::getAvailableBooks)
        .has(
            new Condition<>(
                (books) ->
                    books.stream()
                        .anyMatch((b) -> b.getBookId().equalsIgnoreCase(book.id().toString())),
                ""));
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
                    new IssueBookToUser(user.id().toString())),
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
                            new IssueBookToUser(user.id().toString())),
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
                new IssueBookToUser(user.id().toString())));

    HttpResponse<String> response =
        client
            .toBlocking()
            .exchange(
                HttpRequest.POST(
                    "/issued/" + book.id().toString() + "/return",
                    new ReturnBook(user.id().toString())),
                String.class);

    assertThat(response).extracting(HttpResponse::getStatus).isEqualTo(HttpStatus.OK);
  }

  @Test
  void reserveBook_success() {
    var book =
        bookRepository.insert(
            Book.create(Clock.systemUTC(), new InventoryNumber(UUID.randomUUID().toString())));

    var john = userRepository.insert(User.create("John Doe"));
    var jayne = userRepository.insert(User.create("Jayne Doe"));
    client
        .toBlocking()
        .exchange(
            HttpRequest.POST(
                "/available/" + book.id().toString() + "/issue",
                new IssueBookToUser(john.id().toString())));

    HttpResponse<String> response =
        client
            .toBlocking()
            .exchange(
                HttpRequest.POST(
                    "/issued/" + book.id().toString() + "/reserve",
                    new ReturnBook(jayne.id().toString())),
                String.class);

    assertThat(response).extracting(HttpResponse::getStatus).isEqualTo(HttpStatus.OK);
  }

  @Test
  void getAvailableBook_noBook() {
    var exc =
        assertThrows(
            HttpClientResponseException.class,
            () -> client.toBlocking().exchange(HttpRequest.GET("/available/" + UUID.randomUUID())));

    assertThat(exc.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  void getAvailableBook_success() {
    var book =
        bookRepository.insert(
            Book.create(Clock.systemUTC(), new InventoryNumber(UUID.randomUUID().toString())));

    HttpResponse<AvailableBook> response =
        client
            .toBlocking()
            .exchange(HttpRequest.GET("/available/" + book.id()), AvailableBook.class);

    assertThat(response)
        .isNotNull()
        .extracting(HttpResponse::body)
        .hasFieldOrPropertyWithValue("bookId", book.id().toString());
  }
}
