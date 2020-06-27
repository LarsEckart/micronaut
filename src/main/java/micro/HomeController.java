package micro;

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

  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

  @Get
  public HttpResponse<Void> get() {
    logger.info("Received request to /");
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
    logger.info("Received request to /");
    String test = null;
    test.length();
    return HttpResponse.ok();
  }
}
