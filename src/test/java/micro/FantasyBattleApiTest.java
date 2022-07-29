package micro;

import org.approvaltests.JsonJacksonApprovals;
import org.junit.jupiter.api.Test;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.response.Response;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;

@MicronautTest
class FantasyBattleApiTest {

    @Inject EmbeddedServer server;

    @Test
    void exposes_health_endpoint_due_to_micronaut_management_dependency() {
        Response response = given()
                .port(server.getPort())
                .when()
                .get("/inventory")
                .then()
                .extract()
                .response();

        JsonJacksonApprovals.verifyAsJson(response.getBody().print());
    }

}
