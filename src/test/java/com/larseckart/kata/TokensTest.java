package com.larseckart.kata;

import static io.restassured.RestAssured.given;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import org.approvaltests.JsonJacksonApprovals;
import org.approvaltests.core.Options;
import org.approvaltests.scrubbers.RegExScrubber;
import org.junit.jupiter.api.Test;

@MicronautTest
class TokensTest {

  @Inject
  EmbeddedServer server;

  @Test
  void exposes_health_endpoint_due_to_micronaut_management_dependency() {
    Response response = given()
        .port(server.getPort())
        .body("grant_type=password&username=test&password=1234")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .when()
        .post("/tokens/create")
        .then()
        .extract()
        .response();

    String print = response.getBody().print();
    String json = print.replaceAll("abc\\d\\d\\d\\d", "[TOKEN]");
    JsonJacksonApprovals.verifyAsJson(json, new Options(new RegExScrubber(
        "\\b[A-Z][a-z]{2},\\s\\d{2}\\s[A-Z][a-z]{2}\\s\\d{4}\\s\\d{2}:\\d{2}:\\d{2}\\s[A-Z]{3}\\b",
        "date")));
  }

}
