package micro.messaging;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;

@Controller("/messaging/send")
public class MessageController {

    private TokenRepository repository;

    public MessageController(TokenRepository repository) {
        this.repository = repository;
    }

    @Post(produces = MediaType.TEXT_PLAIN)
    public HttpResponse index(@Header("auth") String auth) {
        if (auth == null || auth.isBlank()) {
            return HttpResponse.unauthorized();
        }

        String[] split = auth.split("_");
        if (split.length != 2) {
            return HttpResponse.unauthorized();
        }
        if (repository.validToken(split[0], split[1])) {
            return HttpResponse.ok();
        }
        return HttpResponse.unauthorized();
    }
}
