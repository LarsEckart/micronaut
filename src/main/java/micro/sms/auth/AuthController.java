package micro.sms.auth;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/sms/auth")
public class AuthController {

    @Get(produces = MediaType.TEXT_PLAIN)
    public HttpResponse index() {
        return HttpResponse.status(HttpStatus.BAD_REQUEST);//.body("success");
    }


}
