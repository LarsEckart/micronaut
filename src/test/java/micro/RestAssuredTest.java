package micro;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.restassured.http.Header;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
