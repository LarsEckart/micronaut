package micro.messaging;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;

import java.util.Map;
import java.util.Optional;

@Controller("/messaging/send")
public class MessageController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MessageController.class);

    private TokenRepository repository;
    private final SMSGateway gateway;

    public MessageController(TokenRepository repository, SMSGateway gateway) {
        this.repository = repository;
        this.gateway = gateway;
    }

    @Post(produces = MediaType.TEXT_PLAIN)
    public HttpResponse index(@Header("auth") String auth, HttpRequest<Map<String, Object>> request) {
        if (auth == null || auth.isBlank()) {
            return HttpResponse.unauthorized();
        }

        String[] split = auth.split("_");
        if (split.length != 2) {
            return HttpResponse.unauthorized();
        }
        if (repository.validToken(split[0], split[1])) {

            Optional<Map<String, Object>> body = request.getBody();

            String text = String.valueOf(body.get().get("text"));
            if (text == null || "null".equals(text)) {
                return HttpResponse.badRequest("text is mandatory");
            }
            String from = String.valueOf(body.get().get("from"));
            if (from == null || "null".equals(from)) {
                return HttpResponse.badRequest("from is mandatory");
            }
            String to = String.valueOf(body.get().get("to"));
            if (to == null || "null".equals(to)) {
                return HttpResponse.badRequest("to is mandatory");
            }
            String displayName = String.valueOf(body.get().get("display_name"));
            if (displayName == null || "null".equals(displayName)) {
                return HttpResponse.badRequest("display_name is mandatory");
            }

            gateway.send(to, text);

            log.info("message sent");

            return HttpResponse.ok();
        }
        return HttpResponse.unauthorized();
    }
}
