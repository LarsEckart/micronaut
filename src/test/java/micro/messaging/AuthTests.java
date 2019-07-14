package micro.messaging;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@MicronautTest
class AuthTests {

    @Inject
    EmbeddedServer server;

    @Test
    void returns_400_when_no_header_sender() {
        given().
            port(server.getPort()).
        when().
            get("/messaging/auth").
        then().
            statusCode(400);
    }

        @Test
    void returns_401_when_header_sender_empty() {
        given().
            port(server.getPort()).
            header("sender", "").
        when().
            get("/messaging/auth").
        then().
            statusCode(400);
    }

    @Test
    void returns_token_for_any_sender() {
        given().
            port(server.getPort()).
            header("sender", "secretSender").
        when().
            get("/messaging/auth").
        then().
            statusCode(200).body(equalTo("42"));
    }

}
