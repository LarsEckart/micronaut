package micro;

import javax.inject.Inject;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

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

    @Test
    void conference_found() {
        given().
            port(server.getPort()).
        when().
            get("/conference/Gr8Conf").
        then().
            statusCode(200).
            body(equalTo("{\"name\":\"Gr8Conf\",\"location\":\"Copenhagen\"}"));
    }

    @Test
    void conference_not_found_empty_optional() {
        given().
            port(server.getPort()).
        when().
            get("/conference/Gr8Confxxx").
        then().
            statusCode(404).
            body(equalTo("{\"_links\":{\"self\":{\"href\":\"/conference/Gr8Confxxx\",\"templated\":false}},\"message\":\"Page Not Found\"}"));
    }
}
