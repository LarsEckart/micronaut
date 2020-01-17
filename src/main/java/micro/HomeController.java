package micro;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller
class HomeController {

  @Get
  public HttpResponse get() {
    System.out.println("Received request to /");
    return HttpResponse.ok();
  }

  @Post
  @Consumes({MediaType.APPLICATION_JSON})
  public HttpResponse post(@Body String text) {
    System.out.println(text);
    return HttpResponse.ok();
  }
}
