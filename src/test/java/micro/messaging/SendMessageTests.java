package micro.messaging;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;

@MicronautTest
public class SendMessageTests {

    @Inject
    EmbeddedServer server;

    @Test
    void bad_request_when_no_auth_header() {
        //language=JSON
        String payload = "{\n"
                + "  \"text\": \"hello world\",\n"
                + "  \"to\": \"01603668822\",\n"
                + "  \"from\": \"0211\",\n"
                + "  \"display_name\": \"ECHO\"\n"
                + "}";

        given().
            port(server.getPort()).
            contentType(ContentType.JSON).
            body(payload).
        when().
            post("/messaging/send").
        then().
            statusCode(400);
    }

    @Test
    void unauthorized_when_auth_header_not_valid() {
        //language=JSON
        String payload = "{\n"
                + "  \"text\": \"hello world\",\n"
                + "  \"to\": \"01603668822\",\n"
                + "  \"from\": \"0211\",\n"
                + "  \"display_name\": \"ECHO\"\n"
                + "}";

        given().
            port(server.getPort()).
            contentType(ContentType.JSON).
            header("auth", "123_").
            body(payload).
        when().
            post("/messaging/send").
        then().
            statusCode(401);
    }
}
