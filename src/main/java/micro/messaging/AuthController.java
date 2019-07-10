package micro.messaging;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;

import java.util.Optional;

@Controller("/messaging/auth")
public class AuthController {

    private TokenRepository repository;

    public AuthController(TokenRepository repository) {
        this.repository = repository;
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    public HttpResponse index(@Header("sender") String sender) {
        if (sender == null) {
            return HttpResponse.badRequest();
        }
        Optional<String> token = repository.getToken(sender);
        if (token.isPresent()) {
            return HttpResponse.ok(token.get());
        }
        return HttpResponse.badRequest("unknown sender");
    }
}
