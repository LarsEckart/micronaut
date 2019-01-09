package micro;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;

import javax.validation.constraints.NotBlank;

@Controller("/")
@Validated
public class HelloController {

    @Get(uri = "/hello/{name}", produces = MediaType.TEXT_PLAIN)
    public String hello(@NotBlank String name) {
        String template = "Hello " + name + "!";
        if ("Lars".equalsIgnoreCase(name)) {
            template += " tubli poiss :-)";
        }
        return template;
    }
}
