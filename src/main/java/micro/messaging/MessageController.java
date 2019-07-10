package micro.messaging;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller("/messaging/send")
public class MessageController {

    @Post(produces = MediaType.TEXT_PLAIN)
    public HttpResponse index() {
        return HttpResponse.ok();
    }
}
