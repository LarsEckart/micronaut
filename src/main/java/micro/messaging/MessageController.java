package micro.messaging;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

import java.util.Map;

@Controller("/messaging/send")
class MessageController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MessageController.class);

    private TokenRepository repository;
    private final SMSGateway gateway;

    public MessageController(TokenRepository repository, SMSGateway gateway) {
        this.repository = repository;
        this.gateway = gateway;
    }

    @Post(produces = MediaType.TEXT_PLAIN)
    public Single<HttpResponse> index(@Header("auth") String auth, @Body Map<String, Object> map) {
        if (auth == null || auth.isBlank()) {
            return Single.just(HttpResponse.unauthorized());
        }

        String[] split = auth.split("_");
        if (split.length != 2) {
            return Single.just(HttpResponse.unauthorized());
        }
        if (repository.validToken(split[0], split[1])) {

            String text = String.valueOf(map.get("text"));
            if (text == null || "null".equals(text)) {
                return Single.just(HttpResponse.badRequest("text is mandatory"));
            }
            String to = String.valueOf(map.get("to"));
            if (to == null || "null".equals(to)) {
                return Single.just(HttpResponse.badRequest("to is mandatory"));
            }

            return gateway.send(to, text).map(m -> HttpResponse.ok(m.get("result")));
        }

        return Single.just(HttpResponse.unauthorized());
    }
}
