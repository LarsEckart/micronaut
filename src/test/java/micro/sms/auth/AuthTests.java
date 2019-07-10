package micro.sms.auth;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;

@MicronautTest
class AuthTests {

    @Inject
    EmbeddedServer server;

    @Test
    void returns_401_when_no_header_sender() {
        given().
            port(server.getPort()).
        when().
            get("/sms/auth").
        then().
            statusCode(400);
    }

}
