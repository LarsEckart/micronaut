package com.larseckart.kata;

import static io.restassured.RestAssured.given;

import com.redis.testcontainers.RedisContainer;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import java.util.Map;
import org.approvaltests.JsonJacksonApprovals;
import org.approvaltests.core.Options;
import org.approvaltests.reporters.FileCaptureReporter;
import org.approvaltests.reporters.Junit3Reporter;
import org.approvaltests.reporters.UseReporter;
import org.approvaltests.scrubbers.RegExScrubber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@MicronautTest
@Testcontainers(disabledWithoutDocker = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@UseReporter(Junit3Reporter.class)
class TokensTest implements TestPropertyProvider {

  @Container
  private static RedisContainer container = new RedisContainer(
      RedisContainer.DEFAULT_IMAGE_NAME.withTag(RedisContainer.DEFAULT_TAG));


  @Override
  public @NonNull Map<String, String> getProperties() {
    if (!container.isRunning()) {
      container.start();
    }
    return Map.of(
        "redis.url", container.getRedisURI(),
        "redis.port", container.getFirstMappedPort().toString(),
        "redis.test", "true");
  }

  @Inject
  EmbeddedServer server;

  @Test
  void test_token_retrieval() {
    Response response =
        given()
            .port(server.getPort())
            .body("grant_type=password&username=test&password=1234")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .when()
            .post("/tokens/create")
            .then()
            .extract()
            .response();
    Response response2 =
        given()
            .port(server.getPort())
            .body("grant_type=password&username=test&password=1234")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .when()
            .post("/tokens/create")
            .then()
            .extract()
            .response();

    String print = response.getBody().print();
    String json = print.replaceAll("abc\\d\\d\\d\\d?", "[TOKEN]");
    JsonJacksonApprovals.verifyAsJson(json, new Options(new RegExScrubber(
        "\\b[A-Z][a-z]{2},\\s\\d{2}\\s[A-Z][a-z]{2}\\s\\d{4}\\s\\d{2}:\\d{2}:\\d{2}\\s[A-Z]{3}\\b",
        "[DATE]")).withReporter(new FileCaptureReporter()));
  }

}
