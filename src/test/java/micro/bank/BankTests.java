package micro.bank;

import javax.inject.Inject;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@MicronautTest
class BankTests {

  @Inject EmbeddedServer server;

  @Test
  void all_accounts_after_migrations() {
    verifyPath("/account");
  }

  @Test
  void single_account_info() {
    verifyPath("/account/EE1420041010050500013M02606");
  }

  private void verifyPath(String path) {
    String response = given()
        .port(server.getPort())
        .when()
        .get(path)
      .then()
        .extract()
        .asString();

    Approvals.verifyJson(response);
  }
}
