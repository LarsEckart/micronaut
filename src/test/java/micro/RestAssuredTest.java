package micro;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@MicronautTest
class RestAssuredTest {

    @Inject
    EmbeddedServer server;

    @Test
    void exposes_health_endpoint_due_to_micronaut_management_dependency() {
        given().
            port(server.getPort()).
        when().
            get("/health").
        then().
            body(equalTo("{\"status\":\"UP\"}"));
    }

    @Test
    void exposes_metrics_endpoint_due_to_micronaut_micrometer_core_dependency() {
        given().
            port(server.getPort()).
        when().
            get("/metrics").
        then().
            statusCode(200);
    }

    @Test
    void exposes_env_endpoint_due_to_micronaut_micrometer_management_dependency() {
        given().
            port(server.getPort()).
        when().
            get("/env").
        then().
            statusCode(200);
    }

    @Test
    void exposes_home_get_endpoint() {
        given().
            port(server.getPort()).
        when().
            get("/").
        then().
            statusCode(200);
    }

    @Test
    void exposes_home_post_endpoint_for_json_requests() {
        given().
            port(server.getPort()).
            body("{}").
            contentType(ContentType.JSON).
        when().
            post("/").
        then().
            statusCode(200);
    }
}
