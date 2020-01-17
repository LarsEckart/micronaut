package micro.bank;

import javax.inject.Inject;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@MicronautTest
class BankTests {

  @Inject EmbeddedServer server;

  @Test
  void all_accounts_after_migrations() {
    given()
        .port(server.getPort())
        .when()
        .get("/account")
        .then()
        .body(
            equalTo(
                "[{\"amount\":111.11,\"iban\":\"EE1420041010050500013M02606\"},{\"amount\":222.22,\"iban\":\"FI89370400440532013000\"}]"));
  }

  @Test
  void single_account_info() {
    given()
        .port(server.getPort())
        .when()
        .get("/account/EE1420041010050500013M02606")
        .then()
        .body("amount", is(111.11f), "iban", equalTo("EE1420041010050500013M02606"));
  }
}
