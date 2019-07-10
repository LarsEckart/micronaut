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
    void expose_endpoint_to_return_information_about_a_users_appointments() {
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
            statusCode(200);
    }
}
