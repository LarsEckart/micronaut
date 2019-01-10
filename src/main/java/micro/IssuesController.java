package micro;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/issues")
public class IssuesController {

    @Get("/{number}")
    String issue(Integer number) {
        return "Issue # " + number + "!";
    }
}
