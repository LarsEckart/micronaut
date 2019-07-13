package micro.messaging;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;

@Controller("/messaging/auth")
public class AuthController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthController.class);

    private TokenRepository repository;
    private final Config config;

    public AuthController(TokenRepository repository, Config config) {
        this.repository = repository;
        this.config = config;
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    public HttpResponse index(@Header("sender") String sender) {
        log.info(config.accountSid);
        if (sender == null || sender.isBlank()) {
            return HttpResponse.badRequest();
        }
        return HttpResponse.ok(repository.getToken(sender));
    }
}
