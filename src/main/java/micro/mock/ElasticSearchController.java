package micro.mock;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("es")
class ElasticSearchController {

  private static final Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);

  @Get(value = "/empty", produces = MediaType.APPLICATION_JSON)
  public HttpResponse read(HttpRequest request) {
    logger.info(request.getBody().toString());
    return HttpResponse.status(HttpStatus.OK).body("{\"took\":1,\"timed_out\":false,\"_shards\":{\"total\":10,\"successful\":10,\"failed\":0},\"hits\":{\"total\":0,\"max_score\":null,\"hits\":[]}}");
  }

}
