package micro.sms.auth;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;

@Controller("/sms/auth")
public class AuthController {

    @Get(produces = MediaType.TEXT_PLAIN)
    public HttpResponse index(@Header("sender") String sender) {
        if (sender == null || sender.isBlank()) {
            return HttpResponse.unauthorized();
        }
        return HttpResponse.badRequest();//.body("success");
    }
}
