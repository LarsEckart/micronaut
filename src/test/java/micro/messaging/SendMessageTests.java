package micro.messaging;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@MicronautTest
class SendMessageTests {

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

    @Test
    void ok_when_auth_header_present_and_valid_token_and_body() {
        Response sender = given().
                    port(server.getPort()).
                    header("sender", "secretSender").
                when().
                    get("/messaging/auth");


        String payload = "{\n"
                + "  \"text\": \"hello world\",\n"
                + "  \"to\": \"01603668822\",\n"
                + "  \"from\": \"0211\",\n"
                + "  \"display_name\": \"ECHO\"\n"
                + "}";

        given().
            port(server.getPort()).
            contentType(ContentType.JSON).
            header("auth", "secretSender_" + sender.getBody().print()).
            body(payload).
        when().
            post("/messaging/send").
        then().
            statusCode(200);
    }

    @Test
    void response_code_400_when_no_text() {
        Response sender = given().
                    port(server.getPort()).
                    header("sender", "secretSender").
                when().
                    get("/messaging/auth");


        String payload = "{\n"
                + "  \"to\": \"01603668822\",\n"
                + "  \"from\": \"0211\",\n"
                + "  \"display_name\": \"ECHO\"\n"
                + "}";

        given().
            port(server.getPort()).
            contentType(ContentType.JSON).
            header("auth", "secretSender_" + sender.getBody().print()).
            body(payload).
        when().
            post("/messaging/send").
        then().
            statusCode(400).
            body(equalTo("text is mandatory"));
    }

    @Test
    void response_code_400_when_no_to() {
        Response sender = given().
                    port(server.getPort()).
                    header("sender", "secretSender").
                when().
                    get("/messaging/auth");


        String payload = "{\n"
                + "  \"text\": \"hello world\",\n"
                + "  \"from\": \"0211\",\n"
                + "  \"display_name\": \"ECHO\"\n"
                + "}";

        given().
            port(server.getPort()).
            contentType(ContentType.JSON).
            header("auth", "secretSender_" + sender.getBody().print()).
            body(payload).
        when().
            post("/messaging/send").
        then().
            statusCode(400).
            body(equalTo("to is mandatory"));
    }

    @Test
    void response_code_400_when_no_from() {
        Response sender = given().
                    port(server.getPort()).
                    header("sender", "secretSender").
                when().
                    get("/messaging/auth");


        String payload = "{\n"
                + "  \"text\": \"hello world\",\n"
                + "  \"to\": \"01603668822\",\n"
                + "  \"display_name\": \"ECHO\"\n"
                + "}";

        given().
            port(server.getPort()).
            contentType(ContentType.JSON).
            header("auth", "secretSender_" + sender.getBody().print()).
            body(payload).
        when().
            post("/messaging/send").
        then().
            statusCode(400).
            body(equalTo("from is mandatory"));
    }

    @Test
    void response_code_400_when_no_display_name() {
        Response sender = given().
                    port(server.getPort()).
                    header("sender", "secretSender").
                when().
                    get("/messaging/auth");


        String payload = "{\n"
                + "  \"text\": \"hello world\",\n"
                + "  \"to\": \"01603668822\",\n"
                + "  \"from\": \"0211\"\n"
                + "}";

        given().
            port(server.getPort()).
            contentType(ContentType.JSON).
            header("auth", "secretSender_" + sender.getBody().print()).
            body(payload).
        when().
            post("/messaging/send").
        then().
            statusCode(400).
            body(equalTo("display_name is mandatory"));
    }
}
