package micro.guitarshack;

import static org.slf4j.LoggerFactory.getLogger;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import org.slf4j.Logger;

@Controller
class StubDataController {

  private static final Logger log = getLogger(StubDataController.class);

  @Get("/default/product")
  public HttpResponse<Product> product(@QueryValue String id) {
    log.info("Received request to /");
    return HttpResponse.ok(new Product(Integer.valueOf(id), 2, 3));
  }

  @Get("/default/sales")
  public HttpResponse<SalesTotal> salesTotal(@QueryValue String id) {
    log.info("Received request to /");
    return HttpResponse.ok(new SalesTotal(1));
  }

}
