package micro;

import java.util.ArrayList;
import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    l.get(0).length();
    return HttpResponse.ok();
  }
}
