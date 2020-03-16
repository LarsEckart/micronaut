package micro.mock;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("es")
class ElasticSearchController {

  @Get(value = "/empty", produces = MediaType.APPLICATION_JSON)
  public HttpResponse read() {
    return HttpResponse.status(HttpStatus.OK).body("{\"took\":1,\"timed_out\":false,\"_shards\":{\"total\":10,\"successful\":10,\"failed\":0},\"hits\":{\"total\":0,\"max_score\":null,\"hits\":[]}}");
  }

}
