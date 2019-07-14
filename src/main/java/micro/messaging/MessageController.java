package micro.messaging;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.reactivex.Flowable;

import java.util.Map;

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
    public Flowable<HttpResponse> index(@Header("auth") String auth, @Body Map<String, Object> map) {
        if (auth == null || auth.isBlank()) {
            return Flowable.just(HttpResponse.unauthorized());
        }

        String[] split = auth.split("_");
        if (split.length != 2) {
            return Flowable.just(HttpResponse.unauthorized());
        }
        if (repository.validToken(split[0], split[1])) {

            String text = String.valueOf(map.get("text"));
            if (text == null || "null".equals(text)) {
                return Flowable.just(HttpResponse.badRequest("text is mandatory"));
            }
            String from = String.valueOf(map.get("from"));
            if (from == null || "null".equals(from)) {
                return Flowable.just(HttpResponse.badRequest("from is mandatory"));
            }
            String to = String.valueOf(map.get("to"));
            if (to == null || "null".equals(to)) {
                return Flowable.just(HttpResponse.badRequest("to is mandatory"));
            }
            String displayName = String.valueOf(map.get("display_name"));
            if (displayName == null || "null".equals(displayName)) {
                return Flowable.just(HttpResponse.badRequest("display_name is mandatory"));
            }

            return gateway.send(to, text).map(m -> HttpResponse.ok());
        }
        return Flowable.just(HttpResponse.unauthorized());
    }
}
