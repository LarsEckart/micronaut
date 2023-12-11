package micro;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.tracing.annotation.NewSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Get
    public HttpResponse<Void> get() {
        log.info("Received request to /");
        return HttpResponse.ok();
    }

    @Post
    @Consumes({MediaType.APPLICATION_JSON})
    public HttpResponse post(@Body String text) {
        System.out.println(text);
        return HttpResponse.ok();
    }

    @Get("/npe")
    public HttpResponse<Void> getNpe() {
        log.info("Received request to /npe");
        List<String> l = new ArrayList<>();
        String none = null;
        l.add(none);
        l.get(0).isEmpty();
        return HttpResponse.ok();
    }

    @Get("/query")
    public HttpResponse<Void> query(@QueryValue(defaultValue = "false") boolean oldEnough) {
        if (oldEnough) {
            return HttpResponse.ok();
        } else {
            return HttpResponse.badRequest();
        }
    }

    @NewSpan("uuid")
    @Get("/uuid")
    public HttpResponse<MyResponse> queryUuid() {
        MyResponse myResponse = new MyResponse(UUID.fromString("65b26f22-b751-411f-b7eb-5dd5c4a96db8"));
        return HttpResponse.ok(myResponse);
    }

    @Get("/uuidzero")
    public HttpResponse<MyResponse> queryUuidZero() {
        MyResponse myResponse = new MyResponse(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        return HttpResponse.ok(myResponse);
    }

    static class MyResponse {

        @JsonInclude
        private UUID uuid;

        public MyResponse(UUID uuid) {
            this.uuid = uuid;
        }

        public UUID getUuid() {
            return uuid;
        }
    }
}
