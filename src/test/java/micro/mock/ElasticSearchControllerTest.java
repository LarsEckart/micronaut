package micro.mock;

import javax.inject.Inject;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@MicronautTest
class ElasticSearchControllerTest {

  @Inject EmbeddedServer server;

  @Test
  void ok_when_auth_header_present_and_valid_token_and_body() {
    given()
        .port(server.getPort())
        .contentType(ContentType.JSON)
    .when()
        .get("/es/empty")
    .then()
        .statusCode(200);
  }
}