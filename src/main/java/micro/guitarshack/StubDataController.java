package micro.guitarshack;

import static org.slf4j.LoggerFactory.getLogger;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import java.util.Random;
import org.slf4j.Logger;

@Controller
class StubDataController {

  private static final Logger log = getLogger(StubDataController.class);
  private final Random random = new Random();

  @Get("/default/product")
  public HttpResponse<Product> product(@QueryValue String id) {
    log.info("Received request to /product");
    return HttpResponse.ok(new Product(Integer.parseInt(id), random.nextInt(10), random.nextInt(45)));
  }

  @Get("/default/sales")
  public HttpResponse<SalesTotal> salesTotal(@QueryValue String id) {
    log.info("Received request to /sales");
    return HttpResponse.ok(new SalesTotal(random.nextInt(20)));
  }

}
