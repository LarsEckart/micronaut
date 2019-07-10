package micro.messaging;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;

import java.util.Optional;

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
        String user = split[0];
        Optional<String> token = repository.getToken(user);
        if (token.isEmpty()) {
            return HttpResponse.unauthorized();
        } else {
            if (token.get().equals(split[1])) {
                return HttpResponse.ok();
            }
            return HttpResponse.unauthorized();
        }

    }
}
