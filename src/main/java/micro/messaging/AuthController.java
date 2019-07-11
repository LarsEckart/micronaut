package micro.messaging;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;

@Controller("/messaging/auth")
public class AuthController {

    private TokenRepository repository;

    public AuthController(TokenRepository repository) {
        this.repository = repository;
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    public HttpResponse index(@Header("sender") String sender) {
        if (sender == null || sender.isBlank()) {
            return HttpResponse.badRequest();
        }
        return HttpResponse.ok(repository.getToken(sender));
    }
}
