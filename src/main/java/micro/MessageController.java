package micro;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import static io.micronaut.http.HttpResponse.ok;

@Controller("/request")
public class MessageController {

    @Get("/hello")
    HttpResponse<String> hello(HttpRequest<?> request) {
        String name = request.getParameters().getFirst("name").orElse("Nobody");

        return ok("Hello " + name).header("x-my-header", "foo");
    }

}
