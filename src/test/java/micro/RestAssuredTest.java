package micro;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@MicronautTest
public class RestAssuredTest {

    @Inject
    EmbeddedServer server;

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
