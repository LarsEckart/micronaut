package micro.sms.auth;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;

import java.util.Map;

@Controller("/sms/auth")
public class AuthController {

    private Map<String, String> tokens = Map.of("123", "secret321", "456", "secret654");

    @Get(produces = MediaType.TEXT_PLAIN)
    public HttpResponse index(@Header("sender") String sender) {
        if (sender == null || sender.isBlank()) {
            return HttpResponse.unauthorized();
        }
        if (tokens.containsKey(sender)) {
            return HttpResponse.ok(tokens.get(sender));
        }
        return HttpResponse.badRequest("unknown sender");
    }
}
