package micro.messaging;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller("/messaging/send")
public class MessageController {

    private TokenRepository repository;

    public MessageController(TokenRepository repository) {
        this.repository = repository;
    }

    @Post(produces = MediaType.TEXT_PLAIN)
    public HttpResponse index(@Header("auth") String auth, HttpRequest<Map<String, Object>> request) {
        if (auth == null || auth.isBlank()) {
            return HttpResponse.unauthorized();
        }

        log.info("auth: " + auth);
        log.info("body: " + request.getBody().get().toString());

        String[] split = auth.split("_");
        if (split.length != 2) {
            return HttpResponse.unauthorized();
        }
        if (repository.validToken(split[0], split[1])) {

            Optional<Map<String, Object>> body = request.getBody();
            String text = (String) body.get().get("text");
            if (text == null) {
                return HttpResponse.badRequest("text is mandatory");
            }
            String from = String.valueOf(body.get().get("from"));
            if (from == null) {
                return HttpResponse.badRequest("from is mandatory");
            }
            String to = String.valueOf(body.get().get("to"));
            if (to == null) {
                return HttpResponse.badRequest("to is mandatory");
            }
            String displayName = String.valueOf(body.get().get("display_name"));
            if (displayName == null) {
                return HttpResponse.badRequest("display_name is mandatory");
            }

            return HttpResponse.ok();
        }
        return HttpResponse.unauthorized();
    }
}
