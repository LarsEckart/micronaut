package micro.messaging;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;

@Controller("/messaging/auth")
class AuthController {

  private static final org.slf4j.Logger log =
      org.slf4j.LoggerFactory.getLogger(AuthController.class);

  private final TokenRepository repository;
  private final AuthConfiguration config;

  public AuthController(TokenRepository repository, AuthConfiguration config) {
    this.repository = repository;
    this.config = config;
  }

  @Get(produces = MediaType.TEXT_PLAIN)
  public HttpResponse index(@Header("sender") String sender) {
    if (sender == null || sender.isBlank()) {
      return HttpResponse.badRequest();
    }
    if (!config.sender.equals(sender)) {
      return HttpResponse.unauthorized();
    }

    return HttpResponse.ok(repository.getToken(sender));
  }
}
