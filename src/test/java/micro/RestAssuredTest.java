package micro;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@MicronautTest
class RestAssuredTest {

    @Inject
    EmbeddedServer server;

    @Test
    void exposes_health_endpoint() {
        given().
            port(server.getPort()).
        when().
            get("/health").
        then().
            body(equalTo("up"));
    }

    @Test
    void makes_request_tomicronaut_server() {
        given().
            port(server.getPort()).
            param("name", "Lars").
        when().
            get("/request/hello").
        then().
            header("x-my-header", equalTo("foo")).
            body(equalTo("Hello Lars"));
    }
}
