package micro;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import io.micronaut.runtime.server.EmbeddedServer;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@MicronautTest
class RestAssuredTest {

  @Inject EmbeddedServer server;

  @Test
  void exposes_health_endpoint_due_to_micronaut_management_dependency() {
    given()
        .port(server.getPort())
        .when()
        .get("/health")
        .then()
        .body(equalTo("{\"status\":\"UP\"}"));
  }

  @Disabled("stopped working with micronaut 2.0")
  @Test
  void exposes_metrics_endpoint_due_to_micronaut_micrometer_core_dependency() {
    given().port(server.getPort()).when().get("/metrics/jvm.memory.used").then().statusCode(200);
  }

  @Test
  void exposes_env_endpoint_due_to_micronaut_micrometer_management_dependency() {
    given().port(server.getPort()).when().get("/env").then().statusCode(200);
  }

  @Test
  void exposes_home_get_endpoint() {
    given().port(server.getPort()).when().get("/").then().statusCode(200);
  }

  @Test
  void exposes_home_post_endpoint_for_json_requests() {
    given()
        .port(server.getPort())
        .body("{}")
        .contentType(ContentType.JSON)
    .when()
        .post("/")
    .then()
        .statusCode(200);
  }

  @Test
  void helpfull_nullpointer() {
    given().
        port(server.getPort()).
    when().
        get("/npe").
    then().
        statusCode(500);
  }

  @Test
  void queryValue() {
    given().
        port(server.getPort()).
    when().
        get("/query?oldEnough=true").
    then().
        statusCode(200);
  }

  @Test
  void queryValue2() {
    given().
        port(server.getPort()).
    when().
        get("/query?oldEnough=false").
    then().
        statusCode(400);
  }

  @Test
  void queryValue3() {
    given().
        port(server.getPort()).
    when().
        get("/query").
    then().
        statusCode(400);
  }

  @Test
  void queryUuid() {
    Response response = given().
        port(server.getPort()).
        when().
        get("/uuid").
        then().
        statusCode(200).
        extract().response();
    ResponseBody body = response.getBody();

    assertThat(body.print()).isEqualTo("{\"uuid\":\"65b26f22-b751-411f-b7eb-5dd5c4a96db8\"}");
  }

  @Test
  void queryUuidZero() {
    Response response = given().
        port(server.getPort()).
        when().
        get("/uuidzero").
        then().
        statusCode(200).
        extract().response();
    ResponseBody body = response.getBody();

    assertThat(body.print()).isEqualTo("{\"uuid\":\"00000000-0000-0000-0000-000000000000\"}");
  }

}
