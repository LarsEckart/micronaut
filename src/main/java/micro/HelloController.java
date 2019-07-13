package micro;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.validation.Validated;

import javax.validation.constraints.NotBlank;

@Controller("/hello")
@Validated
public class HelloController {

    @Get(uri = "/{name}", produces = MediaType.TEXT_PLAIN)
    public String hello(@NotBlank String name) {
        String template = "Hello " + name + "!";
        if ("Lars".equalsIgnoreCase(name)) {
            template += " tubli poiss :-)";
        }
        return template;
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    public String index() {
        return "Gute Nacht";
    }

    @Get("/header")
    public String helloHeader(@Header("x-name") String name) {
        return "best hello for " + name;
    }
}
